package com.wd.play.patterns.behavioral;

import com.wd.play.support.domain.auth.*;

public class ChainOfResponsibilityPatternDemo {

    public static void main(String[] args) {

        AuthenticationProcessor authProcessorChain = getChainOfAuthProcessor();
        boolean isAuthorized = authProcessorChain.isAuthorized(new OAuthTokenProvider());
        System.out.println("isAuthorized with OAuthTokenProvider: "+isAuthorized);

        authProcessorChain = getChainOfAuthProcessor();
        isAuthorized = authProcessorChain.isAuthorized(new SamlAuthenticationProvider());
        System.out.println("isAuthorized with SamlAuthenticationProvider: "+isAuthorized);
    }

    private static AuthenticationProcessor getChainOfAuthProcessor() {
        AuthenticationProcessor oAuthProcessor = new OAuthProcessor(null);
        return new UsernamePasswordProcessor(oAuthProcessor);
    }
}
