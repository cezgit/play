package com.elyzar.play.support.domain.auth;

public abstract class AuthenticationProcessor {

    // next element in chain or responsibility
    public AuthenticationProcessor nextProcessor;

    public AuthenticationProcessor(AuthenticationProcessor nextProcessor) {
        this.nextProcessor = nextProcessor;
    }

    public abstract boolean isAuthorized(AuthenticationProvider authProvider);
}
