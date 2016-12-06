package com.company.processor.renderer;

import com.company.processor.renderer.generic.Renderer;

import java.util.HashMap;
import java.util.Map;

public class RendererRegister {
    private static RendererRegister instance;
    private Map<Renderer.RendererType, Renderer> renders;

    private RendererRegister(){
        renders = new HashMap<>();
    }

    private static void checkIfNeedsInit() {
        if (instance == null)
            instance = new RendererRegister();
    }

    public static void register(Renderer.RendererType renderType, Renderer renderer){
        checkIfNeedsInit();
        instance.renders.put(renderType, renderer);
    }

    public static Renderer getRenderer(Renderer.RendererType renderType){
        checkIfNeedsInit();
        return instance.renders.get(renderType);
    }
}
