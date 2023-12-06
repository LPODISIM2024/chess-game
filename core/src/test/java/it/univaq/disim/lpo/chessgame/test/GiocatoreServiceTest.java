package it.univaq.disim.lpo.chessgame.test;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import it.univaq.disim.lpo.chessgame.core.service.GiocatoreService;
import it.univaq.disim.lpo.chessgame.core.service.ModeEnumeration;
import it.univaq.disim.lpo.chessgame.core.service.impl.UmanoServiceImpl;

public class GiocatoreServiceTest {
	
    @Test
    public void testGetMode() {
        GiocatoreService umanoService = new UmanoServiceImpl();
        ModeEnumeration en = umanoService.getMode();
        System.out.println(en);
        assertTrue(true);
    }

}
