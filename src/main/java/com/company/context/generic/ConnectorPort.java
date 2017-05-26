package com.company.context.generic;

public interface ConnectorPort {
    boolean isConnectable(String thatType);
    boolean isFree();
    <P extends ConnectorPort> void attach(P port);
    void detach();
}