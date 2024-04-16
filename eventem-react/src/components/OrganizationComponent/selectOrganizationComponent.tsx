import { ThemeProvider } from "@emotion/react";
import { Organization } from "../../models/dtos/OrganizationDTO";
import React, { useState, useEffect } from "react";
import { defaultTheme } from "../RegisterComponent/RegisterComponent";
import { Container, CssBaseline, Box, Typography, List, ListItem, ListItemButton, ListItemText, Button } from "@mui/material";
import {organizationService} from "../../api/organizationService";
import { useAuth } from '../../auth/AuthContext';

const SelectOrganizationComponent: React.FC<{onOrganizationSelect: (organization: Organization | null)=> void}> = ({onOrganizationSelect}) => {
const [organizations, setOrganizations] = useState<Organization[]>([]);
const { token, setToken, userId, setUser } = useAuth();
useEffect(() => {

    async function fetchOrganizations() {
                if (!userId || !token) {
            console.log('no userId or token');
            return;
        }
        console.log('userId: '+userId);
        const organizations = await organizationService.getUserOrganizations(userId, token);
        console.log('organizations: '+organizations);
        setOrganizations(organizations);
    }
    fetchOrganizations();
    console.log('current organizations:' + organizations);
}
, []);
const handleListItemClick = (
    event: React.MouseEvent<HTMLDivElement, MouseEvent>,
    id: number,
) => {
    onOrganizationSelect(organizations.find(organization => organization.id === id) || null);
   
}
return (
    <ThemeProvider theme={defaultTheme}>
        <Container component = "main" maxWidth="xs">
            <CssBaseline/>
            <Box sx={{marginTop: 8,
                        display: 'flex',
                        flexDirection: 'column',
                        alignItems: 'center'}}>
                            <Typography component="h1" variant="h5">
                                Select an organization:
                            </Typography>
                            <Box component="div" sx={{ mt: 3 }}>
                                <List component="ul"
                                id="organization-select">
                                    {organizations.map((organization) => (
                                        <ListItem key={organization.id} component="div">
                                            <ListItemButton onClick={(event)=> handleListItemClick(event, organization.id as number)}>
                                                <ListItemText primary={organization.name}/>
                                                </ListItemButton>
                                        </ListItem>

                                    ))}
                                </List>
                                {//<Button variant="contained" onClick={() => fetchOrganizations()}>fetch organizations</Button>
                                }
                            </Box>
                        </Box>
            </Container></ThemeProvider>
)
    }
    export default SelectOrganizationComponent;