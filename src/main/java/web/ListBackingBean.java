package web;

import entities.Users;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import org.primefaces.event.SelectEvent;
import utils.JsfUtils;
import utils.ResourceBundleUtils;

/**
 * Ziarno służy do wyświetlania listy kont użytkowników oraz zapewniania
 * niezbędnych administracyjnych czynności takich jak aktywacja oraz
 * potwierdzanie
 *
 */
@Named(value = "listBackingBean")
@ViewScoped
public class ListBackingBean implements Serializable{

    @Inject
    private UserSession userSession;

    private List<Users> items = new ArrayList<>();
    private DataModel<Users> usersDataModel;
    private String matchLogin;

    /**
     * Metoda służy do pobrania pobrania modelu danych kont użytkowników
     *
     * @return zwraca model danych kont użytkowników
     */
    public DataModel<Users> getAccountDataModel() {
        return usersDataModel;
    }

    public String getMatchLogin() {
        return matchLogin;
    }

    public void setMatchLogin(String matchLogin) {
        this.matchLogin = matchLogin;
    }

    /**
     * Metoda pobiera konto aktualnie zaznaczonego użytkownika z ziarna
     * sesyjnego
     *
     * @return zwracaną wartością jest konto aktualnie zaznaczonego użytkownika
     */
    public Users getSelected() {
        return userSession.getSelectedUser();
    }

    /**
     * Metoda ustawia aktualnie zaznaczone konto użytkownika w ziarnie sesyjnym
     *
     * @param selected zaznaczone konto użytkownika
     */
    public void setSelected(Users selected) {
        userSession.setSelectedUser(selected);
    }

    /**
     * Metoda jest wykorzystywana do pobrania listy kont użytkowników oraz
     * przypisania tej listy do modelu danych prezentowanego w interfejsie
     * użytkownika
     */
    @PostConstruct
    private void initModel() {
        items = userSession.prepareUsersList();
        usersDataModel = new ListDataModel<>(items);
    }

    /**
     * Konstruktor bezparametrowy
     */
    public ListBackingBean() {
    }

    /**
     * Metoda jest wykorzystywana do przesłania właściwego użytkownika do
     * edycji, właściwego tzn. takiego przy którym w tabeli naciśnięto przycisk
     * Edytuj.
     * @return 
     */
    public String beforeEdit() {
        int rowIndex = usersDataModel.getRowIndex();
        userSession.getUserToEdit(items.get(rowIndex));
        return "./edit.xhtml?faces-redirect=true";
    }

    /**
     * Metoda służy do obsługi zdarzenia poprzez wyświetlenie komunikatu o
     * zaznaczonym użytkowniku w interfejsie użytkownika
     *
     * @param event parametr, który wnosi informacje związanie z obsługą
     * zdarzenia zaznaczenia rzędu w tabeli
     */
    public void onRowSelect(SelectEvent event) {
        JsfUtils.addSuccessMessage(ResourceBundleUtils.getResourceBundleLanguageProperty("userSelected"), getSelected().getLogin(), "userListForm:msgs");
    }

    public void filter() {
        items = userSession.filter(matchLogin);
        usersDataModel = new ListDataModel<>(items);
    }

}
