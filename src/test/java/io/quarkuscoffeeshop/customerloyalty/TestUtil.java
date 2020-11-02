package io.quarkuscoffeeshop.customerloyalty;

import io.quarkuscoffeeshop.customerloyalty.domain.LoyaltyMember;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

public class TestUtil {

    /**
     * Provides a Mockito Answer to use when mocking DB inserts
     */
    public static class AssignIdToEntityAnswer implements Answer<Void> {

        private final Long id;

        public AssignIdToEntityAnswer(final Long id) {
            this.id = id;
        }

        @Override
        public Void answer(final InvocationOnMock invocation) throws Throwable {
            final LoyaltyMember loyaltyMember = (LoyaltyMember) invocation.getArguments()[0];
            loyaltyMember.id = id;
            return null;
        }
    }
}
