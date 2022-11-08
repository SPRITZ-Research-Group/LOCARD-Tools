package com.google.ads.mediation;

@Deprecated
public interface h<ADDITIONAL_PARAMETERS, SERVER_PARAMETERS extends k> {
    void destroy();

    Class<ADDITIONAL_PARAMETERS> getAdditionalParametersType();

    Class<SERVER_PARAMETERS> getServerParametersType();
}
