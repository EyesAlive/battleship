

public interface MoveStrategy
{
	public void move(int x, int y);
	public int x();
	public int y();
	public void gameState(GameState state);
	public void lastHit(int x, int y);
}