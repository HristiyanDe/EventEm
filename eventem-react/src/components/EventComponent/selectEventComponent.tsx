import React, { useEffect, useState } from "react";
import { eventService } from "../../api/eventService"; 
import { useAuth } from "../../auth/AuthContext";
import {  Box, TextField, InputAdornment, Menu, MenuItem
} from "@mui/material";
import SearchIcon from '@mui/icons-material/Search';
import { FindEventRequest } from "../../models/FindEventRequest";
interface SelectEventComponentProps {
    onEventSelect: (event: FindEventRequest | null) => void;
}
const SelectEventComponent: React.FC<SelectEventComponentProps> = ({ onEventSelect }) => {

    const { token } = useAuth();
    const [input, setInput] = React.useState<string>('');
    const [events, setEvents] = useState<FindEventRequest[]>([]);
    const [selectedEvent, setSelectedEvent] = useState<FindEventRequest | null>(null);
    const handleInputChange = (value: string) => {
        setInput(value);
        fetchEvents(value);
        console.log(events);
    };
    const fetchEvents = (value: string) => {
            eventService.searchEvents(value, token).then(events => {
                setEvents(events);
            }).catch(error => {
                console.error('Error fetching events:', error);
            });
        };
            const handleSelectEvent = (event: FindEventRequest) => {
        setSelectedEvent(event);
        setEvents([]);
        setInput(event.name + " " + event.date);
        onEventSelect(event);
    };

    return (
        <Box component="form" noValidate sx={{ mt: 1, mb: 3, width: '100%' }}>
                        <TextField
                            onChange={(e) => handleInputChange(e.target.value)}
                            margin="normal"
                            required
                            fullWidth
                            id="search-event-name"
                            label="Search event by name"
                            name="search-event-name"
                            autoComplete="search-event-name"
                            autoFocus
                            InputProps={{
                                startAdornment: (
                                    <InputAdornment position="start">
                                        <SearchIcon />
                                    </InputAdornment>
                                ),
                            }}
                        />
                   <Menu open={events.length > 0 && selectedEvent==null} >
                    {events.map((event) => (
                        <MenuItem key={event.id} onClick={() => handleSelectEvent(event)
                            
                        }>
                            {event.name + " " + event.date}
                        </MenuItem>
                    ))}
                </Menu>
                    </Box>
        
            
                
                
    );
};

export { SelectEventComponent };