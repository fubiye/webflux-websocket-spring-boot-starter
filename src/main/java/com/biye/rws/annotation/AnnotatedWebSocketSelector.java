package com.biye.rws.annotation;

import com.biye.rws.WsControllerRegister;
import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.Ordered;
import org.springframework.core.type.AnnotationMetadata;

public class AnnotatedWebSocketSelector implements ImportSelector, Ordered {
    @Override
    public String[] selectImports(AnnotationMetadata annotationMetadata) {
        return new String[]{WsControllerRegister.class.getName()};
    }

    @Override
    public int getOrder() {
        return HIGHEST_PRECEDENCE;
    }
}
