/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dgm.endpoints;

import common.CommisionTeachersUtils;
import dgm.managers.ExamManagerLocal;
import dgm.managers.TeacherManagerLocal;
import dgm.managers.ThesisManagerLocal;
import entities.Exam;
import entities.Students;
import entities.Teachers;
import entities.Thesis;
import entities.Thesistype;
import exceptions.BusinessException;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.ejb.EJB;
import javax.ejb.Stateful;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

/**
 *
 * @author Damian
 */
@Stateful
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
public class DiplomaGuideEndpoint implements DiplomaGuideEndpointLocal {

    @EJB
    private ExamManagerLocal examManagerLocal;

    @EJB
    private ThesisManagerLocal thesisManagerLocal;

    @EJB
    private TeacherManagerLocal teacherManagerLocal;

    private Thesis thesisToEditByTeacherState;
    private Thesis thesisToEditByStudentState;
    private Exam examToEditState;
    private Exam examToAddCommision;
    private Exam examToEditCommision;
    private Exam examToSetGradeState;

    /**
     * Deleguje utworzenie pracy dyplomowej do thesisManagera
     *
     * @param thesis praca dyplomowa do utworzenia
     * @throws BusinessException wyjątek aplikacyjny
     */
    @Override
    public void createThesis(Thesis thesis) throws BusinessException {
        thesisManagerLocal.createThesis(thesis);
    }

    /**
     * Deleguje pobranie obiektu uwierzytelnionego nauczyciela z bazy do
     * thesisManagera oraz zwraca pobrany obiekt
     *
     * @param login login uwierzytelnionego nauczyciela
     * @return obiekt uwierzytelnionego nauczyciela
     */
    @Override
    public Teachers getLoggedTeacher(String login) {
        return teacherManagerLocal.getLoggedTeacher(login);
    }

    /**
     * Deleguje pobranie mapy nauczycieli oraz liczby prac dyplomowych, które
     * prowadzą w danym roku do thesisManagera oraz zwraca pobraną mapę.
     *
     * @return mapa nauczycieli oraz liczby prac dyplomowych, które prowadzą w
     * danym roku
     */
    @Override
    public Map<Teachers, Integer> getTeachersMap() {
        return thesisManagerLocal.getTeachersMap();
    }

    /**
     * Deleguje pobranie listy wszystkich nauczycieli do thesisManagera oraz
     * zwraca pobraną listę
     *
     * @return lista wszystkich nauczycieli
     */
    @Override
    public List<Teachers> getTeachers() {
        return thesisManagerLocal.getTeachers();
    }

    /**
     * Deleguje pobranie obiektu uwierzytelnionego studenta do thesisManagera
     * oraz zwraca pobrany obiekt
     *
     * @param loggedUserLogin login uwierzytelnionego studenta
     * @return obiekt uwierzytelnionego studenta
     */
    @Override
    public Students getLoggedStudent(String loggedUserLogin) {
        return thesisManagerLocal.getLoggedStudent(loggedUserLogin);

    }

    /**
     * Deleguje pobranie listy typów(rodzajów) pracy dyplomowych do
     * thesisManagera oraz zwraca pobrana listę
     *
     * @return lista typów(rodzajów) prac dyplomowych
     */
    @Override
    public List<Thesistype> getThesisTypeList() {
        return thesisManagerLocal.getThesisTypeList();

    }

    /**
     * Deleguje pobranie wszystkich prac dyplomowych do thesisManagera oraz
     * zwraca pobraną listę wszystkich prac
     *
     * @return lista wszystkch prac dyplomowych
     */
    @Override
    public List<Thesis> getAllThesisList() {
        return thesisManagerLocal.getAllThesisList();
    }

    /**
     * Deleguje pobranie listy prac dyplomowych zawierających w tytule szukaną
     * frazę do thesisManagera oraz zwraca pobraną listę
     *
     * @param phrase fraza, część słowa szukana w tytule pracy dyplomowych
     * @return lista prac dyplomowych zawierająca w tytule szukaną frazę
     */
    @Override
    public List<Thesis> getThesisWithPhrase(String phrase) {
        return thesisManagerLocal.getThesisWithPhrase(phrase);
    }

    /**
     * Deleguje pobranie obiektu pracy dyplomowej do edycji przez nauczyciela do
     * thesisManagera. Zapamiętuje jego stan oraz zwraca pobrany obiekt
     *
     * @param row praca dyplomowa do edycji przez nauczyciela
     * @return pobrany obiekt pracy dyplomowej
     */
    @Override
    public Thesis getThesisToEditByTeacher(Thesis row) {
        thesisToEditByTeacherState = thesisManagerLocal.getThesisToEditByTeacher(row);
        return thesisToEditByTeacherState;
    }

    /**
     * Deleguje akceptację pracy dyplomowej przez nauczyciela do thesisManagera
     *
     * @param thesisToEdit praca dyplomowa do akceptacji
     * @throws BusinessException wyjątek aplikacyjny
     */
    @Override
    public void acceptation(Thesis thesisToEdit) throws BusinessException {
        thesisManagerLocal.acceptThesis(thesisToEditByTeacherState, thesisToEdit);
        thesisToEditByTeacherState = null;
    }

    /**
     * Deleguje pobranie listy prac dyplomowych danego studenta do
     * thesisManagera oraz zwraca pobraną listę
     *
     * @param loggedStudent student, dla którego należy pobrać listę jego prac
     * dyplomowych
     * @return lista prac dyplomowych danego studenta
     */
    @Override
    public List<Thesis> getMyThesis(Students loggedStudent) {
        return thesisManagerLocal.getMyThesis(loggedStudent);
    }

    /**
     * Deleguje pobranie obiektu pracy dyplomowej do edycji przez studenta do
     * thesisManagera. Zapamiętuje stan obiektu oraz zwraca obiekt do edycji
     *
     * @param thesis praca dyplomowa do edycji
     * @return obiekt pracy dyplomowej do edycji przez studenta
     */
    @Override
    public Thesis getThesisToEditByStudent(Thesis thesis) {
        thesisToEditByStudentState = thesisManagerLocal.getThesisToEditByStudent(thesis);
        return thesisToEditByStudentState;
    }

    /**
     * Deleguje edycję pracy dyplomowej przez studenta do thesisManagera
     *
     * @param thesis praca dyplomowa do edycji
     * @throws BusinessException wyjątek aplikacyjny
     */
    @Override
    public void editThesisByStudent(Thesis thesis) throws BusinessException {
        thesisManagerLocal.editThesisByStudent(thesisToEditByStudentState, thesis);
        thesisToEditByStudentState = null;
    }

    /**
     * Deleguje utworzenie obiektu egzaminu do examManagera
     *
     * @param exam obiekt egzaminu do utworzenia
     * @throws BusinessException wyjątek aplikacyjny
     */
    @Override
    public void createExam(Exam exam) throws BusinessException {
        examManagerLocal.createExam(exam);
    }

    /**
     * Deleguje pobranie listy egzaminów dla prac prowadzonych przez danego
     * nauczyciela do examManagera oraz zwraca pobraną listę
     *
     * @param loggedTeacher nauczyciel, dla którego chcemy pobrać listę
     * egzaminów prac dyplomowych, które prowadzi
     * @return lista egzaminów prac dyplomowych, które prowadzi dany nauczyciel
     */
    @Override
    public List<Exam> getExamsByTeacher(Teachers loggedTeacher) {
        return examManagerLocal.getExamsByTeacher(loggedTeacher);
    }

    /**
     * Deleguje pobranie listy prowadzonych prac dyplomowych przez nauczyciela
     * do thesisManagera oraz zwraca pobraną listę
     *
     * @param loggedTeacher nauczyciel, dla którego chcemy pobrać listę
     * prowadzonych prac dyplomowych
     * @return lista prac dyplomowych, w których promotorem jest dany nauczyciel
     */
    @Override
    public List<Thesis> getMyThesisByTeacher(Teachers loggedTeacher) {
        return thesisManagerLocal.getMyThesisByTeacher(loggedTeacher);

    }

    /**
     * Deleguje pobranie listy egzaminów dyplomowych studenta do examManagera
     * oraz zwraca pobraną listę
     *
     * @param loggedStudent student, dla którego będzie pobrana lista jego
     * egzaminów
     * @return lista egzaminów dyplomowych danego studenta
     */
    @Override
    public List<Exam> getMyExamsByStudent(Students loggedStudent) {
        return examManagerLocal.getMyExamsByStudent(loggedStudent);
    }

    /**
     * Deleguje pobranie obiektu egzaminu do edycji z bazy danych. Ustawia jego
     * stan oraz zwraca pobrany obiekt
     *
     * @param e obiekt egzaminu do edycji
     * @return pobrany z bazy obiekt egzaminu do edycji
     */
    @Override
    public Exam getExamToEdit(Exam e) {
        examToEditState = examManagerLocal.getExamToEdit(e);
        return examToEditState;
    }

    /**
     * Deleguje edycję egzaminu przez studenta do metody w examMangerze
     *
     * @param edit obiekt egzaminu do edycji
     * @throws BusinessException wyjątek aplikacyjny
     */
    @Override
    public void editExamByStudent(Exam edit) throws BusinessException {
        examManagerLocal.editExamByStudent(examToEditState, edit);
        examToEditState = null;
    }

    /**
     * Deleguje edycję egzaminu przez nauczyciela do metody w examManagerze
     *
     * @param examToEdit obiekt egzaminu do edycji
     * @throws BusinessException wyjątek aplikacyjny
     */
    @Override
    public void editExamByTeacher(Exam examToEdit) throws BusinessException {
        examManagerLocal.ediExamByTeacher(examToEditState, examToEdit);
        examToEditState = null;
    }

    /**
     * Deleguje pobranie obiektu egzaminu do metody w examManagerze. Zapamiętuje
     * stan obiektu oraz zwraca pobrany obiekt
     *
     * @param e obiekt egzaminu do pobrania z bazy
     * @return pobrany z bazy obiekt egzaminu do dodania w nim komisji
     */
    @Override
    public Exam getExamToAddCommision(Exam e) {
        examToAddCommision = examManagerLocal.getExamToAddCommision(e);
        return examToAddCommision;

    }

    /**
     * Deleguje dodanie komisji egzaminacyjnej do egzaminu do metody w
     * examManagerze
     *
     * @param exam obiekt egzaminu do edycji
     * @param commisionTeachers lista nauczycieli w komisji do przypisania do
     * egzaminu
     * @throws BusinessException wyjątek aplikacyjny
     */
    @Override
    public void addCommission(Exam exam, Set<Teachers> commisionTeachers) throws BusinessException {
        examManagerLocal.addCommision(examToAddCommision, exam, commisionTeachers);
        examToAddCommision = null;
    }

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
    @Override
    public void acceptCommision(Teachers teacher, int rowIndex) throws BusinessException {
        examManagerLocal.acceptCommision(teacher, rowIndex);
    }

    /**
     * Deleguje odrzucenie członkostwa w komisji do metody w examManagerze
     *
     * @param teacher obiekt nauczyciela, który chce odrzucić członkowstwo w
     * komisji
     * @param rowIndex indeks, pod którym znajduje się w liście komisja, w
     * której członkowstwo chce odrzucić nauczyciel
     * @throws BusinessException wyjątek aplikacyjny
     */
    @Override
    public void rejectCommision(Teachers teacher, int rowIndex) throws BusinessException {
        examManagerLocal.rejectCommision(teacher, rowIndex);
    }

    /**
     * Deleguje edycję komisji egzaminacyjnej egzaminu do metody w examManagerze
     *
     * @param exam obiekt egzaminu do edycji
     * @param commisionTeachers lista nauczycieli w komisji do przypisania do
     * egzaminu
     * @throws BusinessException wyjątek aplikacyjny
     */
    @Override
    public void editCommission(Exam exam, Set<Teachers> commisionTeachers) throws BusinessException {
        examManagerLocal.editCommision(examToEditCommision, exam, commisionTeachers);
        examToEditCommision = null;

    }

    /**
     * Deleguje pobranie obiektu egzaminu, do edycji w nim komisji
     * egzaminacyjnej, do metody w examManagerze. Zapamiętuje stan pobranego
     * obiektu oraz zwraca pobrany obiekt
     *
     * @param e obiekt egzaminu do pobrania
     * @return pobrany z bazy obiekt egzaminu do edycji w nim komisji
     * egzaminacyjnej
     */
    @Override
    public Exam getExamToEditCommision(Exam e) {
        examToEditCommision = examManagerLocal.getExamToEditCommision(e);
        return examToEditCommision;

    }

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
    @Override
    public CommisionTeachersUtils setMembersInCommision(Exam exam, Teachers loggedTeacher) {
        return examManagerLocal.setMembersInCommision(exam, loggedTeacher);
    }

    /**
     * Deleguje edycję egzaminu do dedykowanej metody w examManagerze
     *
     * @param examToEdit obiekt egzaminu wybrany do edycji
     * @throws BusinessException wyjątek aplikacyjny
     */
    @Override
    public void setGrade(Exam examToEdit) throws BusinessException {
        examManagerLocal.setGrade(examToSetGradeState, examToEdit);
        examToSetGradeState = null;
    }

    /**
     * Wywołuje metodę pobierającą encję egzaminu z bazy, następnie ustawia stan
     * encji wykorzystywany przy zapewnieniu spójności danych oraz zwraca
     * pobrany obiekt
     *
     * @param e obiekt encji do pobrania z bazy danych
     * @return pobrany z bazy obiekt encji egzaminu
     */
    @Override
    public Exam getExamToSetGrade(Exam e) {
        examToSetGradeState = examManagerLocal.getExamToSetGrade(e);
        return examToSetGradeState;
    }

    @Override
    public void confirmGrade(Exam examToEdit) throws BusinessException {
        examManagerLocal.confirmGrade(examToEditState, examToEdit);
        examToEditState = null;
    }
}
