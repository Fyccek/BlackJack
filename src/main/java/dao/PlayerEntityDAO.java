package dao;

public interface PlayerEntityDAO {

    public Integer findPlayersCredit(Long Id);

    public void delete(PlayerEntity entity);

    public PlayerEntity save(PlayerEntity entity);
}
