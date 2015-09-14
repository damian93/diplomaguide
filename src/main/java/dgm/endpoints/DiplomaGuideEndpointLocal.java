/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dgm.endpoints;

import common.CommisionTeachersUtils;
import entities.Exam;
import entities.Students;
import entities.Teachers;
import entities.Thesis;
import entities.Thesistype;
import exceptions.BusinessException;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.ejb.Local;

/**
 *
 * @author Damian
 */
@Local
public interface DiplomaGuideEndpointLocal {

    /**
     * Deleguje utworzenie pracy dyplomowej do thesisManagera
     *
     * @param t praca dyplomowa do utworzenia
     * @throws BusinessException wyjątek aplikacyjny
     */
    void createThesis(Thesis t) throws BusinessException;

    /**
     * Deleguje pobranie obiektu uwierzytelnionego nauczyciela z bazy do
     * thesisManagera oraz zwraca pobrany obiekt
     *
     * @param login login uwierzytelnionego nauczyciela
     * @return obiekt uwierzytelnionego nauczyciela
     */
    Teachers getLoggedTeacher(String login);

    /**
     * Deleguje pobranie mapy nauczycieli oraz liczby prac dyplomowych, które
     * prowadzą w danym roku do thesisManagera oraz zwraca pobraną mapę.
     *
     * @return mapa nauczycieli oraz liczby prac dyplomowych, które prowadzą w
     * danym roku
     */
    Map<Teachers, Integer> getTeachersMap();

    /**
     * Deleguje pobranie listy wszystkich nauczycieli do thesisManagera oraz
     * zwraca pobraną listę
     *
     * @return lista wszystkich nauczycieli
     */
    List<Teachers> getTeachers();

    /**
     * Deleguje pobranie obiektu uwierzytelnionego studenta do thesisManagera
     * oraz zwraca pobrany obiekt
     *
     * @param loggedUserLogin login uwierzytelnionego studenta
     * @return obiekt uwierzytelnionego studenta
     */
    Students getLoggedStudent(String loggedUserLogin);

    /**
     * Deleguje pobranie listy typów(rodzajów) pracy dyplomowych do
     * thesisManagera oraz zwraca pobrana listę
     *
     * @return lista typów(rodzajów) prac dyplomowych
     */
    List<Thesistype> getThesisTypeList();

    /**
     * Deleguje pobranie wszystkich prac dyplomowych do thesisManagera oraz
     * zwraca pobraną listę wszystkich prac
     *
     * @return lista wszystkch prac dyplomowych
     */
    List<Thesis> getAllThesisList();

    /**
     * Deleguje pobranie listy prac dyplomowych zawierających w tytule szukaną
     * frazę do thesisManagera oraz zwraca pobraną listę
     *
     * @param phrase fraza, część słowa szukana w tytule pracy dyplomowych
     * @return lista prac dyplomowych zawierająca w tytule szukaną frazę
     */
    List<Thesis> getThesisWithPhrase(String phrase);

    /**
     * Deleguje pobranie obiektu pracy dyplomowej do edycji przez nauczyciela do
     * thesisManagera. Zapamiętuje jego stan oraz zwraca pobrany obiekt
     *
     * @param row praca dyplomowa do edycji przez nauczyciela
     * @return pobrany obiekt pracy dyplomowej
     */
    Thesis getThesisToEditByTeacher(Thesis row);

    /**
     * Deleguje akceptację pracy dyplomowej przez nauczyciela do thesisManagera
     *
     * @param thesisToEdit praca dyplomowa do akceptacji
     * @throws BusinessException wyjątek aplikacyjny
     */
    void acceptation(Thesis thesisToEdit) throws BusinessException;

    /**
     * Deleguje pobranie listy prac dyplomowych danego studenta do
     * thesisManagera oraz zwraca pobraną listę
     *
     * @param loggedStudent student, dla którego należy pobrać listę jego prac
     * dyplomowych
     * @return lista prac dyplomowych danego studenta
     */
    List<Thesis> getMyThesis(Students loggedStudent);

    /**
     * Deleguje pobranie obiektu pracy dyplomowej do edycji przez studenta do
     * thesisManagera. Zapamiętuje stan obiektu oraz zwraca obiekt do edycji
     *
     * @param thesis praca dyplomowa do edycji
     * @return obiekt pracy dyplomowej do edycji przez studenta
     */
    Thesis getThesisToEditByStudent(Thesis thesis);

    /**
     * Deleguje edycję pracy dyplomowej przez studenta do thesisManagera
     *
     * @param thesis praca dyplomowa do edycji
     * @throws BusinessException wyjątek aplikacyjny
     */
    void editThesisByStudent(Thesis thesis) throws BusinessException;

    /**
     * Deleguje utworzenie obiektu egzaminu do examManagera
     *
     * @param exam obiekt egzaminu do utworzenia
     * @throws BusinessException wyjątek aplikacyjny
     */
    void createExam(Exam exam) throws BusinessException;

    /**
     * Deleguje pobranie listy egzaminów dla prac prowadzonych przez danego
     * nauczyciela do examManagera oraz zwraca pobraną listę
     *
     * @param loggedTeacher nauczyciel, dla którego chcemy pobrać listę
     * egzaminów prac dyplomowych, które prowadzi
     * @return lista egzaminów prac dyplomowych, które prowadzi dany nauczyciel
     */
    List<Exam> getExamsByTeacher(Teachers loggedTeacher);

    /**
     * Deleguje pobranie listy prowadzonych prac dyplomowych przez nauczyciela
     * do thesisManagera oraz zwraca pobraną listę
     *
     * @param loggedTeacher nauczyciel, dla którego chcemy pobrać listę
     * prowadzonych prac dyplomowych
     * @return lista prac dyplomowych, w których promotorem jest dany nauczyciel
     */
    List<Thesis> getMyThesisByTeacher(Teachers loggedTeacher);

    /**
     * Deleguje pobranie listy egzaminów dyplomowych studenta do examManagera
     * oraz zwraca pobraną listę
     *
     * @param loggedStudent student, dla którego będzie pobrana lista jego
     * egzaminów
     * @return lista egzaminów dyplomowych danego studenta
     */
    List<Exam> getMyExamsByStudent(Students loggedStudent);

    /**
     * Deleguje pobranie obiektu egzaminu do edycji z bazy danych. Ustawia jego
     * stan oraz zwraca pobrany obiekt
     *
     * @param e obiekt egzaminu do edycji
     * @return pobrany z bazy obiekt egzaminu do edycji
     */
    Exam getExamToEdit(Exam e);

    /**
     * Deleguje edycję egzaminu przez studenta do metody w examMangerze
     *
     * @param edit obiekt egzaminu do edycji
     * @throws BusinessException wyjątek aplikacyjny
     */
    void editExamByStudent(Exam edit) throws BusinessException;

    /**
     * Deleguje edycję egzaminu przez nauczyciela do metody w examManagerze
     *
     * @param examToEdit obiekt egzaminu do edycji
     * @throws BusinessException wyjątek aplikacyjny
     */
    void editExamByTeacher(Exam examToEdit) throws BusinessException;

    /**
     * Deleguje pobranie obiektu egzaminu do metody w examManagerze. Zapamiętuje
     * stan obiektu oraz zwraca pobrany obiekt
     *
     * @param e obiekt egzaminu do pobrania z bazy
     * @return pobrany z bazy obiekt egzaminu do dodania w nim komisji
     */
    Exam getExamToAddCommision(Exam e);

    /**
     * Deleguje dodanie komisji egzaminacyjnej do egzaminu do metody w
     * examManagerze
     *
     * @param exam obiekt egzaminu do edycji
     * @param commisionTeachers lista nauczycieli w komisji do przypisania do
     * egzaminu
     * @throws BusinessException wyjątek aplikacyjny
     */
    void addCommission(Exam exam, Set<Teachers> commisionTeachers) throws BusinessException;

    /**
     * Deleguje akceptację członkostwa w komisji egzaminacyjnej do metody w
     * examManagerze
     *
     * @param teacher obiekt nauczyciela, który chce zaakceptować członkowstwo w
     * komisji
     * @param rowIndex indeks, pod którym znajduje się w liście komisja, w
     * której członkowstwo chce zaakceptować nauczyciel
     * @throws BusinessException wyjątek aplikacyjny
     */
    void acceptCommision(Teachers teacher, int rowIndex) throws BusinessException;

    /**
     * Deleguje odrzucenie członkostwa w komisji do metody w examManagerze
     *
     * @param teacher obiekt nauczyciela, który chce odrzucić członkowstwo w
     * komisji
     * @param rowIndex indeks, pod którym znajduje się w liście komisja, w
     * której członkowstwo chce odrzucić nauczyciel
     * @throws BusinessException wyjątek aplikacyjny
     */
    void rejectCommision(Teachers teacher, int rowIndex) throws BusinessException;

    /**
     * Deleguje edycję komisji egzaminacyjnej egzaminu do metody w examManagerze
     *
     * @param exam obiekt egzaminu do edycji
     * @param commisionTeachers lista nauczycieli w komisji do przypisania do
     * egzaminu
     * @throws BusinessException wyjątek aplikacyjny
     */
    void editCommission(Exam exam, Set<Teachers> commisionTeachers) throws BusinessException;

    /**
     * Deleguje pobranie obiektu egzaminu, do edycji w nim komisji
     * egzaminacyjnej, do metody w examManagerze. Zapamiętuje stan pobranego
     * obiektu oraz zwraca pobrany obiekt
     *
     * @param e obiekt egzaminu do pobrania
     * @return pobrany z bazy obiekt egzaminu do edycji w nim komisji
     * egzaminacyjnej
     */
    Exam getExamToEditCommision(Exam e);

    /**
     * Deleguje ustawienie członków komisji egzaminacyjnej do dedykowanej metody
     * w examManagerze. Wykrzystywana wyłącznie do prezentacji danych
     * użytkownikowi.
     *
     * @param exam obiekt egzaminu wybrany do ustawienia członków komisji
     * @param loggedTeacher uwierzytelniony nauczyciel
     * @return obiekt klasy CommisionTeachersUtils zawierający dane przygotowane
     * do wartstwy prezentacji
     */
    CommisionTeachersUtils setMembersInCommision(Exam exam, Teachers loggedTeacher);

    /**
     * Deleguje edycję egzaminu do dedykowanej metody w examManagerze
     *
     * @param examToEdit obiekt egzaminu wybrany do edycji
     * @throws BusinessException wyjątek aplikacyjny
     */
    void setGrade(Exam examToEdit) throws BusinessException;

    /**
     * Wywołuje metodę pobierającą encję egzaminu z bazy, następnie ustawia stan
     * encji wykorzystywany przy zapewnieniu spójności danych oraz zwraca
     * pobrany obiekt
     *
     * @param e obiekt encji do pobrania z bazy danych
     * @return pobrany z bazy obiekt encji egzaminu
     */
    Exam getExamToSetGrade(Exam e);

}
