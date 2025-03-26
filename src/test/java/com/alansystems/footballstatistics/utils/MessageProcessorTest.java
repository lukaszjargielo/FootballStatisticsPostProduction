package com.alansystems.footballstatistics.utils;

import com.alansystems.footballstatistics.model.TeamStatistics;
import com.alansystems.footballstatistics.service.ResultService;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class MessageProcessorTest {

    @ParameterizedTest
    @CsvSource({
            "5, 12, 8, 4.0",
            "6, 13, 9, 3.67",
            "2, 3, 6, 4.5",
            "4, 9, 8, 4.25",
            "6, 11, 15, 4.33"
    })

    public void testAverageGoals(int numberOfEventPlayed, int goalsScored, int goalsConceded, double expectedAverageGoals) {
        TeamStatistics mockTeamStatistics = mock(TeamStatistics.class);

        when(mockTeamStatistics.getNumberOfPlayedEvents()).thenReturn(numberOfEventPlayed);
        when(mockTeamStatistics.getSumOfGoalsScored()).thenReturn(goalsScored);
        when(mockTeamStatistics.getSumOfGoalsConceded()).thenReturn(goalsConceded);

        ResultService resultService = new ResultService();

        resultService.updateAverageGoals(mockTeamStatistics);

        verify(mockTeamStatistics, times(1)).setAverageAmountOfGoalsInAllEvents(expectedAverageGoals);
    }
}