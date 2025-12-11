import React, { useEffect, useState } from "react";
import { Venue } from "../../models/dtos/VenueDTO";
import { ThemeProvider } from "@emotion/react";
import { defaultTheme } from "../RegisterComponent/RegisterComponent";
import {
    List,
    MenuItem,
    Box,
    Container,
    CssBaseline,
    Typography,
    Grid,
    TextField,
    Button,
    ListItem,
    ListItemText,
    ListItemButton
} from "@mui/material";
import { venueService } from "../../api/venueService";
import { useAuth } from "../../auth/AuthContext";

const SelectVenueComponent: React.FC<{ onVenueSelect: (venue: Venue | null) => void }> = ({ onVenueSelect }) => {
    const { token } = useAuth();

    const [venues, setVenues] = useState<Venue[]>([]);
    const [filters, setFilters] = useState({
        name: "",
        city: "",
        address: ""
    });

    const [selectedVenue, setSelectedVenue] = useState<Venue | null>(null);

    useEffect(() => {
        async function fetchVenues() {
            const response = await venueService.fetchVenues(token as string);
            setVenues(response);
        }

        fetchVenues();
    }, [token]);

    const handleInputChange = (e: React.ChangeEvent<HTMLInputElement>) => {
        const { name, value } = e.target;
        setFilters(prev => ({ ...prev, [name]: value }));
    };

    const handleFilterSubmit = async (e: React.MouseEvent<HTMLButtonElement>) => {
        e.preventDefault();
        try {
            const response = await venueService.getVenuesFiltered(
                token as string,
                filters.name,
                filters.city,
                filters.address
            );
            setVenues(response);
        } catch (err) {
            console.error(err);
        }
    };

    const handleListItemClick = (
        event: React.MouseEvent<HTMLDivElement>,
        id: string
    ) => {
        const venueObj = venues.find(v => v.id === id) || null;
        setSelectedVenue(venueObj);
    };

    const handleSubmitSelection = () => {
        onVenueSelect(selectedVenue);
    };

    return (
        <ThemeProvider theme={defaultTheme}>
            <Container component="main" maxWidth="xs">
                <CssBaseline />
                <Box
                    sx={{
                        marginTop: 8,
                        display: "flex",
                        flexDirection: "column",
                        alignItems: "center"
                    }}
                >
                    <Typography component="h1" variant="h5">
                        Filter Venues
                    </Typography>

                    

                    <Box component="div">
                        <Grid container spacing={2}>
                            <List component="ul" id="venue-select">
                                {venues.map(venue => (
                                    <ListItem key={venue.id}>
                                        <ListItemButton
                                            selected={selectedVenue?.id === venue.id}
                                            onClick={e =>
                                                handleListItemClick(e, venue.id as string)
                                            }
                                        >
                                            <ListItemText
                                                primary={venue.name}
                                                secondary={venue.address}
                                            />
                                        </ListItemButton>
                                    </ListItem>
                                ))}
                            </List>
                        </Grid>

                        <Button
                            onClick={handleSubmitSelection}
                            fullWidth
                            variant="contained"
                            sx={{ mt: 3, mb: 2 }}
                            disabled={!selectedVenue}
                        >
                            OK
                        </Button>
                    </Box>
                </Box>
            </Container>
        </ThemeProvider>
    );
};

export default SelectVenueComponent;
