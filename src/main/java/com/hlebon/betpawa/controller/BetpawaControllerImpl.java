package com.hlebon.betpawa.controller;

import com.hlebon.betpawa.protos.BalanceRequest;
import com.hlebon.betpawa.protos.BalanceResponse;
import com.hlebon.betpawa.protos.BetpawaServiceGrpc;
import com.hlebon.betpawa.protos.DepositRequest;
import com.hlebon.betpawa.protos.Error;
import com.hlebon.betpawa.protos.VoidResponse;
import com.hlebon.betpawa.protos.WithdrawRequest;
import io.grpc.stub.StreamObserver;
import org.springframework.stereotype.Service;

@Service
public class BetpawaControllerImpl extends BetpawaServiceGrpc.BetpawaServiceImplBase {

    @Override
    public void deposit(final DepositRequest request, final StreamObserver<VoidResponse> responseObserver) {


        VoidResponse response = VoidResponse.newBuilder()
                .setError(Error.newBuilder().setMessage("Yeee, boy").build()).build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void withdraw(WithdrawRequest request, StreamObserver<VoidResponse> responseObserver) {
        super.withdraw(request, responseObserver);
    }

    @Override
    public void balance(BalanceRequest request, StreamObserver<BalanceResponse> responseObserver) {
        super.balance(request, responseObserver);
    }
}
