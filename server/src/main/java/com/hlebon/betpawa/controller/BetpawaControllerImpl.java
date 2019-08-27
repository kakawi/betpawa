package com.hlebon.betpawa.controller;

import com.hlebon.betpawa.entity.Wallet;
import com.hlebon.betpawa.mapper.api.wallet.BalanceResponseMapper;
import com.hlebon.betpawa.mapper.api.wallet.DepositRequestMapper;
import com.hlebon.betpawa.mapper.api.VoidResponseMapper;
import com.hlebon.betpawa.mapper.api.wallet.WithDrawRequestMapper;
import com.hlebon.betpawa.protos.BalanceRequest;
import com.hlebon.betpawa.protos.BalanceResponse;
import com.hlebon.betpawa.protos.BetpawaServiceGrpc;
import com.hlebon.betpawa.protos.DepositRequest;
import com.hlebon.betpawa.protos.VoidResponse;
import com.hlebon.betpawa.protos.WithdrawRequest;
import com.hlebon.betpawa.service.wallet.BetpawaService;
import com.hlebon.betpawa.service.ServiceException;
import io.grpc.stub.StreamObserver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BetpawaControllerImpl extends BetpawaServiceGrpc.BetpawaServiceImplBase {

    private final BetpawaService betpawaService;
    private final DepositRequestMapper depositRequestMapper;
    private final VoidResponseMapper voidResponseMapper;
    private final WithDrawRequestMapper withDrawRequestMapper;
    private final BalanceResponseMapper balanceResponseMapper;

    @Autowired
    public BetpawaControllerImpl(
            final BetpawaService betpawaService,
            final DepositRequestMapper depositRequestMapper,
            final VoidResponseMapper voidResponseMapper,
            final WithDrawRequestMapper withDrawRequestMapper,
            final BalanceResponseMapper balanceResponseMapper
    ) {
        this.betpawaService = betpawaService;
        this.depositRequestMapper = depositRequestMapper;
        this.voidResponseMapper = voidResponseMapper;
        this.withDrawRequestMapper = withDrawRequestMapper;
        this.balanceResponseMapper = balanceResponseMapper;
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
    public void withdraw(final WithdrawRequest request, final StreamObserver<VoidResponse> responseObserver) {
        OperationResult<Void> operationResult = new OperationResult<>();

        try {
            betpawaService.withdraw(withDrawRequestMapper.mapToEntity(request));
        } catch (final ServiceException e) {
            operationResult.setError(generateOperationError(e.getMessage()));
        }

        VoidResponse response = voidResponseMapper.mapToProto(operationResult);

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void balance(final BalanceRequest request, final StreamObserver<BalanceResponse> responseObserver) {
        OperationResult<List<Wallet>> operationResult = new OperationResult<>();

        try {
            List<Wallet> balance = betpawaService.balance(request.getUserId());
            operationResult.setResult(balance);
        } catch (final ServiceException e) {
            operationResult.setError(generateOperationError(e.getMessage()));
        }

        BalanceResponse response = balanceResponseMapper.mapToProto(operationResult);

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    private OperationResult.OperationError generateOperationError(final String message) {
        return new OperationResult.OperationError(message);
    }
}
