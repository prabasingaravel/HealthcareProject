import axios from "axios";

const PATIENT_BASE_URL = "http://localhost:8081/patients";

const BEARER_TOKEN = "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJTYW5qZWV2ZSIsImV4cCI6MTYxNDg2NjE4NSwiaWF0IjoxNjE0ODMwMTg1fQ.k8MSsPUdtikj13v0I5EXnW8K0v3kDsa_JlwJjYMSk6Y";

class PatientService {

    getAllPatients() {
        const CURRENT_URL = PATIENT_BASE_URL + "/"
        return axios.get(CURRENT_URL, {
            headers:
                { "Authorization": BEARER_TOKEN }
        });
    }

    addPatient(patient) {
        const CURRENT_URL = PATIENT_BASE_URL + "/"
        return axios.post(CURRENT_URL, patient, {
            headers:
                { "Authorization": BEARER_TOKEN }
        });
    }

    getPatientById(id) {
        const CURRENT_URL = PATIENT_BASE_URL + "/" + id;
        return axios.get(CURRENT_URL, {
            headers:
                { "Authorization": BEARER_TOKEN }
        });
    }

    updatePatient(patient) {
        const CURRENT_URL = PATIENT_BASE_URL + "/"
        return axios.put(CURRENT_URL, patient, {
            headers: { 
                "Authorization": BEARER_TOKEN
             }
        });
    }

    deletePatient(id) {
        const CURRENT_URL = PATIENT_BASE_URL + "/" + id;
        return axios.delete(CURRENT_URL, {
            headers:
                { "Authorization": BEARER_TOKEN }
        });
    }
}

export default new PatientService()