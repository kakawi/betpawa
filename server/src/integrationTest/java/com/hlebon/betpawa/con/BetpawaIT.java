package com.hlebon.betpawa.con;

import com.hlebon.betpawa.configuration.RepositoryConfig;
import com.hlebon.betpawa.controller.BetpawaControllerImpl;
import com.hlebon.betpawa.protos.BalanceRequest;
import com.hlebon.betpawa.protos.BalanceResponse;
import com.hlebon.betpawa.protos.Currency;
import com.hlebon.betpawa.protos.DepositRequest;
import com.hlebon.betpawa.protos.Error;
import com.hlebon.betpawa.protos.VoidResponse;
import com.hlebon.betpawa.protos.WithdrawRequest;
import io.grpc.stub.StreamObserver;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = RepositoryConfig.class)
public class BetpawaIT {

    @Autowired
    private BetpawaControllerImpl betpawaController;

    @Test
    /**
     * 1. Make a withdrawal of USD 200 for user with id 1. Must return "insufficient_funds".
     * 2. Make a deposit of USD 100 to user with id 1.
     * 3. Check that all balances are correct
     * 4. Make a withdrawal of USD 200 for user with id 1. Must return "insufficient_funds".
     * 5. Make a deposit of EUR 100 to user with id 1.
     * 6. Check that all balances are correct
     * 7. Make a withdrawal of USD 200 for user with id 1. Must return "insufficient_funds".
     * 8. Make a deposit of USD 100 to user with id 1.
     * 9. Check that all balances are correct
     * 10. Make a withdrawal of USD 200 for user with id 1. Must return "ok".
     * 11. Check that all balances are correct
     * 12. Make a withdrawal of USD 200 for user with id 1. Must return "insufficient_funds".
     */
    public void test() {
        WithdrawRequest build = WithdrawRequest.newBuilder()
                .setUserId(1)
                .setAmount(200L)
                .setCurrency(Currency.USD)
                .build();
        StreamObserver<VoidResponse> voidStreamObserver = mock(StreamObserver.class);

        // 1. Make a withdrawal of USD 200 for user with id 1. Must return "insufficient_funds".
        betpawaController.withdraw(build, voidStreamObserver);

        ArgumentCaptor<VoidResponse> voidResponseArgumentCaptor = ArgumentCaptor.forClass(VoidResponse.class);
        verify(voidStreamObserver).onNext(voidResponseArgumentCaptor.capture());

        VoidResponse actualResponse1 = voidResponseArgumentCaptor.getValue();
        assertNotNull(actualResponse1.getError());
        Error error1 = actualResponse1.getError();
        assertEquals("insufficient funds", error1.getMessage());

        // 2. Make a deposit of USD 100 to user with id 1.
        DepositRequest request2 = DepositRequest.newBuilder()
                .setUserId(1)
                .setAmount(100L)
                .setCurrency(Currency.USD)
                .build();

        betpawaController.deposit(request2, voidStreamObserver);

        verify(voidStreamObserver, times(2)).onNext(voidResponseArgumentCaptor.capture());
        VoidResponse response2 = voidResponseArgumentCaptor.getValue();
        assertFalse(response2.hasError());

        // 3. Check that all balances are correct
        BalanceRequest request3 = BalanceRequest.newBuilder()
                .setUserId(1)
                .build();
        StreamObserver<BalanceResponse> balanceStreamObserver = mock(StreamObserver.class);
        betpawaController.balance(request3, balanceStreamObserver);

        ArgumentCaptor<BalanceResponse> balanceResponseArgumentCaptor = ArgumentCaptor.forClass(BalanceResponse.class);
        verify(balanceStreamObserver).onNext(balanceResponseArgumentCaptor.capture());
        BalanceResponse response3 = balanceResponseArgumentCaptor.getValue();

        assertEquals(2, response3.getBalancesCount());
    }
}
