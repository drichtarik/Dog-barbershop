package cz.muni.fi.pa165.service;

import cz.muni.fi.pa165.dto.AddressDTO;
import cz.muni.fi.pa165.entities.Address;
import org.dozer.Mapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.mockito.Mockito.*;

/**
 * Tests for correct contract implementation defined by {@link CustomerService} interface
 *
 * @author Dominik Gmiterko
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:api-config.xml"})
public class BeanMappingServiceTest {

    @Mock
    private Mapper dozer;

    @Autowired
    @InjectMocks
    private BeanMappingService beanMappingService;

    private Address address;
    private AddressDTO addressDTO;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        address = new Address("Testing Avenue", 22, "Testero", 2356, "Testing Republic");
        addressDTO = new AddressDTO("Testing Avenue", 22, "Testero", 2356, "Testing Republic");
    }

    @Test
    public void testMapToNull(){

        Address empty = null;

        AddressDTO result = beanMappingService.mapTo(empty, AddressDTO.class);

        verify(dozer, times(0)).map(any(), any());
        assertSame(null, result);
    }

    @Test
    public void testMapToAddressDTOSingle(){
        when(dozer.map(address, AddressDTO.class)).thenReturn(addressDTO);

        AddressDTO result = beanMappingService.mapTo(address, AddressDTO.class);

        verify(dozer, times(1)).map(address, AddressDTO.class);
        assertSame(addressDTO, result);
    }


    @Test
    public void testMapToAddressSingle(){
        when(dozer.map(addressDTO, Address.class)).thenReturn(address);

        Address result = beanMappingService.mapTo(addressDTO, Address.class);

        verify(dozer, times(1)).map(addressDTO, Address.class);
        assertSame(address, result);
    }


    @Test
    public void testMapToCollectionEmpty(){
        when(dozer.map(address, AddressDTO.class)).thenReturn(addressDTO);

        List<AddressDTO> result = beanMappingService.mapTo(new ArrayList<Address>(), AddressDTO.class);

        verify(dozer, times(0)).map(address, AddressDTO.class);
        assertEquals(new ArrayList<AddressDTO>(), result);
    }

    @Test
    public void testMapToCollection(){
        when(dozer.map(address, AddressDTO.class)).thenReturn(addressDTO);

        ArrayList<Address> original = new ArrayList<Address>();
        original.add(address);
        original.add(address);
        original.add(address);

        List<AddressDTO> result = beanMappingService.mapTo(original, AddressDTO.class);

        List<AddressDTO> expected = new ArrayList<AddressDTO>();
        expected.add(addressDTO);
        expected.add(addressDTO);
        expected.add(addressDTO);

        verify(dozer, times(3)).map(address, AddressDTO.class);
        assertEquals(expected, result);
    }
}