package com.alansystems.footballstatistics.utils;

import com.alansystems.footballstatistics.model.TeamStatistics;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

public class MessageProcessorTest {
    @Test
    public void testUpdateAverageGoals() {
        TeamStatistics mockTeamStatistics = mock(TeamStatistics.class);

        when(mockTeamStatistics.getSumOfGoalsScored()).thenReturn(13);
        when(mockTeamStatistics.getSumOfGoalsConceded()).thenReturn(9);
        when(mockTeamStatistics.getNumberOfPlayedEvents()).thenReturn(6);

        MessageProcessor messageProcessor = new MessageProcessor();

        messageProcessor.updateAverageGoals(mockTeamStatistics);

        verify(mockTeamStatistics, times(1)).setAverageAmountOfGoalsInAllEvents(3.67);

    }
}