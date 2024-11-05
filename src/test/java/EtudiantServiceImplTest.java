

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import tn.esprit.tpfoyer.TpFoyerApplication;
import tn.esprit.tpfoyer.entity.Etudiant;
import tn.esprit.tpfoyer.repository.EtudiantRepository;
import tn.esprit.tpfoyer.service.EtudiantServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest(classes = TpFoyerApplication.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@ActiveProfiles("test")
public class EtudiantServiceImplTest {

    @Mock
    private EtudiantRepository etudiantRepository;

    @InjectMocks
    private EtudiantServiceImpl etudiantService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testRetrieveAllEtudiants() {
        List<Etudiant> etudiants = new ArrayList<>();
        etudiants.add(new Etudiant());
        etudiants.add(new Etudiant());

        when(etudiantRepository.findAll()).thenReturn(etudiants);

        List<Etudiant> result = etudiantService.retrieveAllEtudiants();

        assertEquals(2, result.size());
        verify(etudiantRepository, times(1)).findAll();
    }

    @Test
    public void testRetrieveEtudiant() {
        Etudiant etudiant = new Etudiant();
        etudiant.setIdEtudiant(1L);

        when(etudiantRepository.findById(1L)).thenReturn(Optional.of(etudiant));

        Etudiant result = etudiantService.retrieveEtudiant(1L);

        assertNotNull(result);
        assertEquals(1L, result.getIdEtudiant());
        verify(etudiantRepository, times(1)).findById(1L);
    }

    @Test
    public void testAddEtudiant() {
        Etudiant etudiant = new Etudiant();

        when(etudiantRepository.save(etudiant)).thenReturn(etudiant);

        Etudiant result = etudiantService.addEtudiant(etudiant);

        assertNotNull(result);
        verify(etudiantRepository, times(1)).save(etudiant);
    }

    @Test
    public void testModifyEtudiant() {
        Etudiant etudiant = new Etudiant();
        etudiant.setIdEtudiant(1L);

        when(etudiantRepository.save(etudiant)).thenReturn(etudiant);

        Etudiant result = etudiantService.modifyEtudiant(etudiant);

        assertNotNull(result);
        assertEquals(1L, result.getIdEtudiant());
        verify(etudiantRepository, times(1)).save(etudiant);
    }

    @Test
    public void testRemoveEtudiant() {
        Long etudiantId = 1L;

        doNothing().when(etudiantRepository).deleteById(etudiantId);

        etudiantService.removeEtudiant(etudiantId);

        verify(etudiantRepository, times(1)).deleteById(etudiantId);
    }

    @Test
    public void testRecupererEtudiantParCin() {
        Etudiant etudiant = new Etudiant();
        etudiant.setCinEtudiant(123456789L);

        when(etudiantRepository.findEtudiantByCinEtudiant(123456789L)).thenReturn(etudiant);

        Etudiant result = etudiantService.recupererEtudiantParCin(123456789L);

        assertNotNull(result);
        assertEquals(123456789L, result.getCinEtudiant());
        verify(etudiantRepository, times(1)).findEtudiantByCinEtudiant(123456789L);
    }
}
