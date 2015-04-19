

public interface Observer
{
	public void updateMoves(MoveStrategy move);
	public void updateShowBoard();
	public boolean updatePlacement(ShipStrategy ship_strategy);
}