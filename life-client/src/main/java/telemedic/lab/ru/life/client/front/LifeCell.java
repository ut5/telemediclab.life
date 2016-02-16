package telemedic.lab.ru.life.client.front;

/**
 *
 * @author UT5
 */
public class LifeCell {
    private Boolean state;

    public LifeCell() {
        this(null);
    }

    public LifeCell(Boolean state) {
        this.state = state;
    }
    
    public Boolean isLiving() {
        return state;
    }

    public void setLife(Boolean state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "LifeCell{" + "state=" + state + '}';
    }
}
