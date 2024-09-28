package com.github.gorbunovalex.caug;

import com.intellij.DynamicBundle;
import kotlin.Suppress;
import kotlin.jvm.JvmStatic;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.PropertyKey;

import java.util.function.Supplier;

@NonNls
public final class MyBundle extends DynamicBundle {
    private static final String BUNDLE = "messages.MyBundle";

    public MyBundle() {
        super(BUNDLE);
    }

    @Suppress(names = "unused")
    @JvmStatic
    public @NotNull Supplier<@Nls String> messagePointer(@PropertyKey(resourceBundle = BUNDLE) String key, Object... params) {
        return getLazyMessage(key, params);
    }
}