package com.microservices.vitalsignmoduleservice.Controller;

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
import com.module.vitalsignmodule.Controller.VitalSignController;
import com.module.vitalsignmodule.Dto.VitalSignDto;
import com.module.vitalsignmodule.ServiceImpl.VitalSignServiceImpl;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = VitalSignModuleServiceApplication.class)
public class VitalSignControllerTest {

	@MockBean
	private VitalSignServiceImpl vitalSignServiceImpl;
	
	@MockBean
	private PatientClient patientClient;
	
	@Autowired
	private VitalSignController vitalSignController;
	
	@Test
	public void addVitalSignTest() {
		VitalSignDto vitalSignDto = VitalSignPrototype.vitalSignDto();
		when(vitalSignServiceImpl.addVitalSign(vitalSignDto)).thenReturn(vitalSignDto);
		VitalSignDto vitalSignDtoResponse = vitalSignController.addVitalSign(vitalSignDto);
		assertEquals(vitalSignDtoResponse, vitalSignDto);
	}
	
	@Test
	public void updateVitalSignTest() {
		VitalSignDto vitalSignDto = VitalSignPrototype.vitalSignDto();
		when(vitalSignServiceImpl.updateVitalSign(vitalSignDto)).thenReturn(vitalSignDto);
		VitalSignDto vitalSignDtoResponse = vitalSignController.updateVitalSign(vitalSignDto);
		assertEquals(vitalSignDtoResponse, vitalSignDto);
	}
	
	@Test
	public void getVitalSignByIdTest() {
		VitalSignDto vitalSignDto = VitalSignPrototype.vitalSignDto();
		when(vitalSignServiceImpl.getVitalSignById(vitalSignDto.getPatientId(),
				vitalSignDto.getCheckupDate())).thenReturn(vitalSignDto);
		VitalSignDto vitalSignDtoResponse = vitalSignController.getVitalSignById(vitalSignDto.getPatientId(),
				vitalSignDto.getCheckupDate());
		assertEquals(vitalSignDtoResponse, vitalSignDto);
	}
	
	@Test
	public void deleteVitalSignTest() {
		String messageExpected ="Vital Sign is deleted with id 12";
		VitalSignDto vitalSignDto = VitalSignPrototype.vitalSignDto();
		when(vitalSignServiceImpl.deleteVitalSign(vitalSignDto.getPatientId(),
				vitalSignDto.getCheckupDate())).thenReturn(messageExpected);
		String vitalSignResponse = vitalSignController.deleteVitalSign(vitalSignDto.getPatientId(), vitalSignDto.getCheckupDate());
		assertEquals(vitalSignResponse, messageExpected);
	}
}
