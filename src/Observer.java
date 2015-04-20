

public interface Observer
{
	public GameState updateMoves(MoveStrategy move);
	public void updateShowBoard(boolean is_player);
	public boolean updatePlacement(ShipStrategy ship_strategy);

}