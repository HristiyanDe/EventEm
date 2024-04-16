import { CategoryRequest } from "./CategoryRequest";
import { Organization } from "./dtos/OrganizationDTO";
import { EventStatusEnum } from "./enums/EventStatusEnum";
export interface EventRequest {
    name: string;
    eventStatus: EventStatusEnum;
    startDate: string;
    endDate: string;
    venueId: number;
    organizationId: number,
    description: string;
    maxAttendees: number;
    categories: CategoryRequest[];
    }
