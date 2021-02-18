package com.microservices.vitalsignmoduleservice.Service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.microservices.vitalsignmoduleservice.Prototype.VitalSignPrototype;
import com.module.vitalsignmodule.VitalSignModuleServiceApplication;
import com.module.vitalsignmodule.Client.PatientClient;
import com.module.vitalsignmodule.Dto.VitalSignDto;
import com.module.vitalsignmodule.Repository.VitalSignRepository;
import com.module.vitalsignmodule.ServiceImpl.VitalSignServiceImpl;
import com.module.vitalsignmodule.Util.VitalSignConverter;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = VitalSignModuleServiceApplication.class)
public class VitalSignServiceImplTest {
	
	@MockBean
	private VitalSignRepository vitalSignRepository;
	
	@MockBean
	private PatientClient patientClient;
	
	@Autowired
	private VitalSignServiceImpl vitalSignServiceImpl;
	
	@Test
	public void addVitalSignTest() {
		VitalSignDto vitalSignDto = VitalSignPrototype.vitalSignDto();
		when(vitalSignRepository.save(VitalSignConverter.convertToVitalSignEntity(vitalSignDto)))
		.thenReturn(VitalSignConverter.convertToVitalSignEntity(vitalSignDto));
		VitalSignDto vitalSignDtoResponse = vitalSignServiceImpl.addVitalSign(vitalSignDto);
		assertEquals(vitalSignDtoResponse, vitalSignDto);
	}
	
	@Test
	public void updateVitalSignTest() {
		VitalSignDto vitalSignDto = VitalSignPrototype.vitalSignDto();
		when(vitalSignRepository.findByPatientIdAndCheckupDate(vitalSignDto.getPatientId(),
				vitalSignDto.getCheckupDate())).thenReturn(VitalSignConverter.convertToVitalSignEntity(vitalSignDto));
		when(vitalSignRepository.save(VitalSignConverter.convertToVitalSignEntity(vitalSignDto)))
		.thenReturn(VitalSignConverter.convertToVitalSignEntity(vitalSignDto));
		VitalSignDto vitalSignDtoResponse = vitalSignServiceImpl.updateVitalSign(vitalSignDto);
		assertEquals(vitalSignDtoResponse, vitalSignDto);
	}
	
	@Test
	public void getVitalSignByIdTest() {
		VitalSignDto vitalSignDto = VitalSignPrototype.vitalSignDto();
		when(vitalSignRepository.findByPatientIdAndCheckupDate(vitalSignDto.getPatientId(),
				vitalSignDto.getCheckupDate())).thenReturn(VitalSignConverter.convertToVitalSignEntity(vitalSignDto));
		VitalSignDto vitalSignDtoResponse = vitalSignServiceImpl.getVitalSignById(vitalSignDto.getPatientId(),
				vitalSignDto.getCheckupDate());
		assertEquals(vitalSignDtoResponse, vitalSignDto);
	}
	
	@Test
	public void deleteVitalSignTest() {
		String messageExpected ="Vital Sign is deleted with id 12";
		VitalSignDto vitalSignDto = VitalSignPrototype.vitalSignDto();
		when(vitalSignRepository.findByPatientIdAndCheckupDate(vitalSignDto.getPatientId(),
				vitalSignDto.getCheckupDate())).thenReturn(VitalSignConverter.convertToVitalSignEntity(vitalSignDto));
		String vitalSignResponse = vitalSignServiceImpl.deleteVitalSign(vitalSignDto.getPatientId(), vitalSignDto.getCheckupDate());
		assertEquals(vitalSignResponse, messageExpected);
	}
}
