package com.microservices.vitalsignmoduleservice.Service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
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
import com.module.vitalsignmodule.Dto.PatientDto;
import com.module.vitalsignmodule.Dto.VitalSignDto;
import com.module.vitalsignmodule.Model.VitalSign;
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
		VitalSign vitalSign = VitalSignPrototype.vitalSign();
		when(vitalSignRepository.save(vitalSign)).thenReturn(vitalSign);
		VitalSignDto vitalSignDtoResponse = vitalSignServiceImpl.addVitalSign(VitalSignConverter.convertToVitalSignDto(vitalSign));
		assertEquals(VitalSignConverter.convertToVitalSignEntity(vitalSignDtoResponse), vitalSign);
	}
	
	@Test
	public void updateVitalSignTest() {
		VitalSign vitalSign = VitalSignPrototype.vitalSign();
		when(vitalSignRepository.findByPatientIdAndCheckupDate(vitalSign.getPatientId(),
				vitalSign.getCheckupDate())).thenReturn(vitalSign);
		vitalSignServiceImpl.updateVitalSign(VitalSignConverter.convertToVitalSignDto(vitalSign));
		verify(vitalSignRepository,times(1)).save(vitalSign);
	}
	
	@Test
	public void getVitalSignByIdTest() {
		VitalSign vitalSign = VitalSignPrototype.vitalSign();
		when(vitalSignRepository.findByPatientIdAndCheckupDate(vitalSign.getPatientId(),
				vitalSign.getCheckupDate())).thenReturn(vitalSign);
		VitalSignDto vitalSignDtoResponse = vitalSignServiceImpl.getVitalSignById(vitalSign.getPatientId(),
				vitalSign.getCheckupDate());
		assertEquals(VitalSignConverter.convertToVitalSignEntity(vitalSignDtoResponse), vitalSign);
	}
	
	@Test
	public void getPatientByIdTest() {
		PatientDto patientDto = VitalSignPrototype.patientDto();
		when(patientClient.getPatientById(patientDto.getPatientId())).thenReturn(patientDto);
		PatientDto patientDtoResponse = vitalSignServiceImpl.getPatientById(patientDto.getPatientId());
		assertEquals(patientDtoResponse, patientDto);
	}
	
	@Test
	public void deleteVitalSignTest() {
		VitalSign vitalSign = VitalSignPrototype.vitalSign();
		when(vitalSignRepository.findByPatientIdAndCheckupDate(vitalSign.getPatientId(),
				vitalSign.getCheckupDate())).thenReturn(vitalSign);
		vitalSignServiceImpl.deleteVitalSign(vitalSign.getPatientId(), vitalSign.getCheckupDate());
		verify(vitalSignRepository,times(1)).delete(vitalSign);
	}
}
