package telemedic.lab.ru.life.client.front;

/**
 *
 * @author UT5
 */
public class LifeCell {
    private Boolean state;

    public LifeCell() {
        this.state = null;
    }

    public Boolean getState() {
        return state;
    }

    public void setState(Boolean state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "LifeCell{" + "state=" + state + '}';
    }
}
