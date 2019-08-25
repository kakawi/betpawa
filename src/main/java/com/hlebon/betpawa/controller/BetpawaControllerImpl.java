package com.hlebon.betpawa.controller;

import com.hlebon.betpawa.mapper.api.DepositRequestMapper;
import com.hlebon.betpawa.mapper.api.VoidResponseMapper;
import com.hlebon.betpawa.protos.BalanceRequest;
import com.hlebon.betpawa.protos.BalanceResponse;
import com.hlebon.betpawa.protos.BetpawaServiceGrpc;
import com.hlebon.betpawa.protos.DepositRequest;
import com.hlebon.betpawa.protos.VoidResponse;
import com.hlebon.betpawa.protos.WithdrawRequest;
import com.hlebon.betpawa.service.BetpawaService;
import com.hlebon.betpawa.service.ServiceException;
import io.grpc.stub.StreamObserver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BetpawaControllerImpl extends BetpawaServiceGrpc.BetpawaServiceImplBase {

    private final BetpawaService betpawaService;
    private final DepositRequestMapper depositRequestMapper;
    private final VoidResponseMapper voidResponseMapper;

    @Autowired
    public BetpawaControllerImpl(
            final BetpawaService betpawaService,
            final DepositRequestMapper depositRequestMapper,
            final VoidResponseMapper voidResponseMapper
    ) {
        this.betpawaService = betpawaService;
        this.depositRequestMapper = depositRequestMapper;
        this.voidResponseMapper = voidResponseMapper;
    }

    @Override
    public void deposit(final DepositRequest request, final StreamObserver<VoidResponse> responseObserver) {

        OperationResult<Void> operationResult = new OperationResult<>();

        try {
            betpawaService.deposit(depositRequestMapper.mapToEntity(request));
        } catch (final ServiceException e) {
            operationResult.setError(generateOperationError(e.getMessage()));
        }

        VoidResponse response = voidResponseMapper.mapToProto(operationResult);

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

    private OperationResult.OperationError generateOperationError(final String message) {
        return new OperationResult.OperationError(message);
    }
}
