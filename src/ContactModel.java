import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class ContactModel extends AbstractListModel <Contact>{

    List <Contact> contactList = new ArrayList<>();

    @Override
    public int getSize() {
        return contactList.size();
    }

    @Override
    public Contact getElementAt(int index) {

        return contactList.get(index);
    }

    @Override
    public String toString() {
        return "ContactModel{" +
                "contactList=" + contactList +
                '}';
    }

    public void add(Contact c){
        contactList.add(c);
        fireContentsChanged(this, 0, getSize());
    }

    public void remove(Contact c){
        contactList.remove(c);
        fireContentsChanged(this, 0, getSize());
    }

    public void refresh (){
        fireContentsChanged(this, 0, getSize());
    }
}
