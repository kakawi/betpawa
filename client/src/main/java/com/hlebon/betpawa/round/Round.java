package com.hlebon.betpawa.round;

import com.hlebon.betpawa.protos.BetpawaServiceGrpc;

public interface Round {

    void start(BetpawaServiceGrpc.BetpawaServiceBlockingStub stub, int userId);
}
