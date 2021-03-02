import 'primeicons/primeicons.css';
import 'primereact/resources/themes/saga-blue/theme.css';
import 'primereact/resources/primereact.css';
import { Dropdown } from 'primereact/dropdown';
import React, { useState, useEffect, useRef } from 'react';
import { DataTable } from 'primereact/datatable';
import { Column } from 'primereact/column';
import { Calendar } from 'primereact/calendar';
import AuditService from '../services/AuditService';
import './DataTableStyles.css'

const HealthcareAuditTrail = () => {
    const [auditEvents, setAuditEvents] = useState([]);
    const [expandedRows, setExpandedRows] = useState(null);
    const isMounted = useRef(false);
    const [selectedServiceName, setSelectedServiceName] = useState(null);
    const [selectedDate, setSelectedDate] = useState(null);
    const dt = useRef(null);

    const serviceNames = [
        'User', 'Patient', 'VitalSign'
    ];

    useEffect(() => {
        if (isMounted.current) {
        }
    }, [expandedRows]);

    useEffect(() => {
        isMounted.current = true;
        AuditService.getAudits().then(response => setAuditEvents(response.data));
    }, []);

    const filterDate = (value, filter) => {
        if (filter === undefined || filter === null || (typeof filter === 'string' && filter.trim() === '')) {
            return true;
        }

        if (value === undefined || value === null) {
            return false;
        }

        return value === formatDate(filter);
    }

    const formatDate = (date) => {
        let month = date.getMonth() + 1;
        let day = date.getDate();

        if (month < 10) {
            month = '0' + month;
        }

        if (day < 10) {
            day = '0' + day;
        }

        return date.getFullYear() + '-' + month + '-' + day;
    }

    const onDateChange = (e) => {
        dt.current.filter(e.value, 'logDate', 'custom');
        setSelectedDate(e.value);
    }

    const onServiceNameChange = (e) => {
        dt.current.filter(e.value, 'serviceName', 'equals');
        setSelectedServiceName(e.value);
    }

    const dateBodyTemplate = (rowData) => {
        return (
            <React.Fragment>
                <span>{rowData.logDate}</span>
            </React.Fragment>
        );
    }

    const serviceNameBodyTemplate = (rowData) => {
        return (
            <React.Fragment>
                <span className={`customer-badge status-${rowData.serviceName}`}>{rowData.serviceName}</span>
            </React.Fragment>
        );
    }

    const serviceNameItemTemplate = (option) => {
        return <span className={`customer-badge status-${option}`}>{option}</span>;
    }

    const rowExpansionTemplate = (responeseData) => {
        return (
            <div>
                <table className = "table table-striped">
                    <thead>
                    <tr>
                        <th>Fields</th>
                        <th>Old Data</th>
                        <th>New Data</th>
                    </tr>
                    {
                        responeseData.data.fieldName.map(
                            (list,index)=>
                                <tr key={list}>
                                    <td>{list}</td>
                                    <td>{responeseData.data.oldValue[index]}</td>
                                    <td>{responeseData.data.newValue[index]}</td>
                                </tr>
                            )
                    }
                    </thead>   
                </table>
            </div>
        );
    }

    const dateFilter = <Calendar value={selectedDate} onChange={onDateChange} dateFormat="yy-mm-dd" className="p-column-filter" placeholder="Updated Date"/>;
    const serviceNameFilter = <Dropdown value={selectedServiceName} options={serviceNames} onChange={onServiceNameChange} itemTemplate={serviceNameItemTemplate} placeholder="Select a Status" className="p-column-filter" showClear />;
    return (
        <div className="datatable-filter-demo">
            <h5 style={{ padding: "10px 10px"}}>Healthcare Audit</h5>
            <div className="card">
                <DataTable ref={dt} value={auditEvents} expandedRows={expandedRows} onRowToggle={(e) => setExpandedRows(e.data)}
                    rowExpansionTemplate={rowExpansionTemplate} dataKey="id" expander>
                    <Column expander style={{ width: '4em' }} />
                    <Column field="logDate" header="Updated Date" sortable body={dateBodyTemplate} filter filterElement={dateFilter} filterFunction={filterDate} />
                    <Column field="serviceName" header="Service Name" sortable body={serviceNameBodyTemplate} filter filterElement={serviceNameFilter}/>
                    <Column field="request" header="Actions" sortable />
                    <Column field="action" header="Description" sortable />
                    <Column field="userName" header="User Name" sortable />
                </DataTable>
            </div>
        </div>
    );
}

export default HealthcareAuditTrail;