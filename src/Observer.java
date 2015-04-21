

public interface Observer
{
	public GameState updateMoves(MoveStrategy move);
	public void updateShowBoard(boolean is_player);
	public int updatePlacement(ShipStrategy ship_strategy);

}