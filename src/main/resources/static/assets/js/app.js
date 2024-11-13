import React, { useState, useEffect } from 'react';
import axios from 'axios';
import ReactDOM from 'react-dom';


    useEffect(() => {
        // Gọi API để lấy danh sách người dùng
        axios.get('http://localhost:8080/api/users')
            .then(response => {
                setUsers(response.data);
            })
            .catch(error => {
                console.error('There was an error fetching the users!', error);
            });
    }, []);

    const columns = [
        {
            name: 'Rendering engine',
            selector: row => row.renderingEngine,
            sortable: true,
        },
        {
            name: 'Browser',
            selector: row => row.browser,
            sortable: true,
        },
        {
            name: 'Platform(s)',
            selector: row => row.platform,
            sortable: true,
        },
        {
            name: 'Engine version',
            selector: row => row.engineVersion,
            sortable: true,
        },
        {
            name: 'CSS grade',
            selector: row => row.cssGrade,
            sortable: true,
        },
    ];

    return (
        <DataTable
            title="Data Table With Full Features"
            columns={columns}
            data={data}
            pagination
        />
    );


ReactDOM.render(<DataTableComponent />, document.getElementById('react-data-table'));