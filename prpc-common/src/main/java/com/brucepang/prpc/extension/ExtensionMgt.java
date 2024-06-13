package com.brucepang.prpc.extension;

import com.brucepang.prpc.scope.ScopeModel;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * @author BrucePang
 */
public class ExtensionMgt implements ExtensionAccessor {

    private final ConcurrentMap<Class<?>, ExtensionLoader<?>> extensionLoadersMap = new ConcurrentHashMap<>(64);

    private final ExtensionScope scope;

    private final ExtensionMgt parent;

    private final ScopeModel scopeModel;

    public ExtensionMgt(ExtensionScope scope, ExtensionMgt parent, ScopeModel scopeModel) {
        this.scope = scope;
        this.parent = parent;
        this.scopeModel = scopeModel;
    }


    @Override
    public ExtensionMgt getExtensionMgt() {
        return this;
    }


    @Override
    public <T> ExtensionLoader<T> getExtensionLoader(Class<T> type) {
       if (null == type) {
           throw new IllegalArgumentException("Extension type == null");
       }
       if (!type.isInterface()){
           throw new IllegalArgumentException("Extension type (" + type + ") is not an interface!");
       }
       if (!hasExtensionAnnotation(type)) {
           throw new IllegalArgumentException("Extension type (" + type + ") is not an extension, because it is NOT annotated with @" + SPI.class.getSimpleName() + "!");
       }
       // get the extension loader
         ExtensionLoader<T> loader = (ExtensionLoader<T>) extensionLoadersMap.get(type);
            if (loader == null) {
                extensionLoadersMap.putIfAbsent(type, ExtensionLoader.create(type));
                loader = (ExtensionLoader<T>) extensionLoadersMap.get(type);
            }
            return loader;
    }


    private static boolean hasExtensionAnnotation(Class<?> type) {
        return type.isAnnotationPresent(SPI.class);
    }
}
