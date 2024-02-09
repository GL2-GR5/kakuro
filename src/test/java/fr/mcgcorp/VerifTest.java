package fr.mcgcorp;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestWatcher;

import java.util.Optional;

public class VerifTest implements TestWatcher {

    @Override
    public void testSuccessful(ExtensionContext context) {
        System.out.println("[\033[34;1m" + context.getDisplayName() + "\033[0m] \033[32;1m\tOK\033[0m");
    }

    @Override
    public void testFailed(ExtensionContext context, Throwable cause) {
        System.out.println("[\033[34;1m" + context.getDisplayName() + "\033[0m] \033[31;1m\tKO\033[0m");
    }

    @Override
    public void testDisabled(ExtensionContext context, Optional<String> reason) {
        System.out.println("[\033[34;1m" + context.getDisplayName() + "\033[0m] \033[33;1m\tDÉSACTIVÉ\033[0m");

    }

    @Override
    public void testAborted(ExtensionContext context, Throwable cause) {
        System.out.println("[\033[34;1m" + context.getDisplayName() + "\033[0m] \033[33;1m\tAVORTÉ\033[0m");
    }
}
