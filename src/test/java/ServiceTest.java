import dao.PlayerEntity;
import dao.PlayerEntityDAOImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import static org.junit.Assert.*;


/**
 * Test class for test the service.
 *
 * */
@RunWith(MockitoJUnitRunner.class)
public class ServiceTest {

    /**
     * Mocked PlayerEntityDAOImpl instance.
     */
    @Mock
    private PlayerEntityDAOImpl playerEntityDAO;

    /**
     * Test method for test the {@link PlayerEntityDAOImpl#findPlayerbyName(String)} method.
     * */
    @Test
    public void testfindPlayerbyName(){
        Integer credit = 4200;
        playerEntityDAO.save(createTestEntity());
        Mockito.when(playerEntityDAO.findPlayerbyName("Pista")).thenReturn(createTestEntity());
        PlayerEntity actual = playerEntityDAO.findPlayerbyName("Pista");
        assertEquals("Pista", actual.getMyname());
        assertEquals(credit, actual.getCredit());
        Mockito.verify(playerEntityDAO).findPlayerbyName("Pista");
    }

    /**
     * Method for help in the test.
     * @return  created playerEntity just for testing.
     *
     * */
    private PlayerEntity createTestEntity(){
        PlayerEntity playerEntity = new PlayerEntity();
        playerEntity.setmyName("Pista");
        playerEntity.setCredit(4200);
        return playerEntity;
    }
}
