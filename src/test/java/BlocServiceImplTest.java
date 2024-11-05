import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.TestMethodOrder;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import tn.esprit.tpfoyer.TpFoyerApplication;
import tn.esprit.tpfoyer.repository.BlocRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;

import org.mockito.MockitoAnnotations;

import tn.esprit.tpfoyer.entity.Bloc;
import tn.esprit.tpfoyer.service.BlocServiceImpl;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest(classes = TpFoyerApplication.class)
@TestMethodOrder(OrderAnnotation.class)
@ActiveProfiles("test")
public class BlocServiceImplTest {
    @Mock
    private BlocRepository blocRepository;

    @InjectMocks
    private BlocServiceImpl blocService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testRetrieveAllBlocs() {
        List<Bloc> blocs = new ArrayList<>();
        blocs.add(new Bloc());
        blocs.add(new Bloc());

        when(blocRepository.findAll()).thenReturn(blocs);

        List<Bloc> result = blocService.retrieveAllBlocs();

        assertEquals(2, result.size());
        verify(blocRepository, times(1)).findAll();
    }

    @Test
    public void testRetrieveBlocsSelonCapacite() {
        List<Bloc> blocs = new ArrayList<>();
        Bloc bloc1 = new Bloc();
        bloc1.setCapaciteBloc(10);
        Bloc bloc2 = new Bloc();
        bloc2.setCapaciteBloc(5);
        blocs.add(bloc1);
        blocs.add(bloc2);

        when(blocRepository.findAll()).thenReturn(blocs);

        List<Bloc> result = blocService.retrieveBlocsSelonCapacite(6);

        assertEquals(1, result.size());
        assertTrue(result.contains(bloc1));
        verify(blocRepository, times(1)).findAll();
    }

    @Test
    public void testRetrieveBloc() {
        Bloc bloc = new Bloc();
        bloc.setIdBloc(1L);

        when(blocRepository.findById(1L)).thenReturn(Optional.of(bloc));

        Bloc result = blocService.retrieveBloc(1L);

        assertNotNull(result);
        assertEquals(1L, result.getIdBloc());
        verify(blocRepository, times(1)).findById(1L);
    }

    @Test
    public void testAddBloc() {
        Bloc bloc = new Bloc();

        when(blocRepository.save(bloc)).thenReturn(bloc);

        Bloc result = blocService.addBloc(bloc);

        assertNotNull(result);
        verify(blocRepository, times(1)).save(bloc);
    }

    @Test
    public void testModifyBloc() {
        Bloc bloc = new Bloc();
        bloc.setIdBloc(1L);

        when(blocRepository.save(bloc)).thenReturn(bloc);

        Bloc result = blocService.modifyBloc(bloc);

        assertNotNull(result);
        assertEquals(1L, result.getIdBloc());
        verify(blocRepository, times(1)).save(bloc);
    }

    @Test
    public void testRemoveBloc() {
        Long blocId = 1L;

        doNothing().when(blocRepository).deleteById(blocId);

        blocService.removeBloc(blocId);

        verify(blocRepository, times(1)).deleteById(blocId);
    }

    @Test
    public void testTrouverBlocsSansFoyer() {
        List<Bloc> blocs = new ArrayList<>();
        Bloc bloc1 = new Bloc();
        Bloc bloc2 = new Bloc();
        blocs.add(bloc1);
        blocs.add(bloc2);

        when(blocRepository.findAllByFoyerIsNull()).thenReturn(blocs);

        List<Bloc> result = blocService.trouverBlocsSansFoyer();

        assertEquals(2, result.size());
        verify(blocRepository, times(1)).findAllByFoyerIsNull();
    }

    @Test
    public void testTrouverBlocsParNomEtCap() {
        List<Bloc> blocs = new ArrayList<>();
        Bloc bloc1 = new Bloc();
        bloc1.setNomBloc("Bloc A");
        bloc1.setCapaciteBloc(10);
        blocs.add(bloc1);

        when(blocRepository.findAllByNomBlocAndCapaciteBloc("Bloc A", 10)).thenReturn(blocs);

        List<Bloc> result = blocService.trouverBlocsParNomEtCap("Bloc A", 10);

        assertEquals(1, result.size());
        assertEquals("Bloc A", result.get(0).getNomBloc());
        assertEquals(10, result.get(0).getCapaciteBloc());
        verify(blocRepository, times(1)).findAllByNomBlocAndCapaciteBloc("Bloc A", 10);
    }

}
