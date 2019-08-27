package com.hlebon.betpawa.controller;

import com.google.protobuf.Empty;
import com.hlebon.betpawa.mapper.api.VoidResponseMapper;
import com.hlebon.betpawa.mapper.api.user.CreateUserRequestMapperImpl;
import com.hlebon.betpawa.protos.CreateUserRequest;
import com.hlebon.betpawa.protos.UserServiceGrpc;
import com.hlebon.betpawa.protos.VoidResponse;
import com.hlebon.betpawa.service.ServiceException;
import com.hlebon.betpawa.service.UserService;
import io.grpc.stub.StreamObserver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserControllerImpl extends UserServiceGrpc.UserServiceImplBase {

    private final UserService userService;
    private final CreateUserRequestMapperImpl createUserRequestMapper;
    private final VoidResponseMapper voidResponseMapper;

    @Autowired
    public UserControllerImpl(
            final UserService userService,
            final CreateUserRequestMapperImpl createUserRequestMapper,
            final VoidResponseMapper voidResponseMapper
    ) {
        this.userService = userService;
        this.createUserRequestMapper = createUserRequestMapper;
        this.voidResponseMapper = voidResponseMapper;
    }

    @Override
    public void create(final CreateUserRequest request, final StreamObserver<VoidResponse> responseObserver) {

        OperationResult<Void> operationResult = new OperationResult<>();

        try {
            userService.create(createUserRequestMapper.mapToEntity(request));
        } catch (final ServiceException e) {
            operationResult.setError(generateOperationError(e.getMessage()));
        }

        VoidResponse response = voidResponseMapper.mapToProto(operationResult);

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void deleteAll(final Empty request, final StreamObserver<VoidResponse> responseObserver) {
        userService.deleteAll();

        responseObserver.onNext(VoidResponse.newBuilder().build());
        responseObserver.onCompleted();
    }

    private OperationResult.OperationError generateOperationError(final String message) {
        return new OperationResult.OperationError(message);
    }
}
