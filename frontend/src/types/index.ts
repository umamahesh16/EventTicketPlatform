import { ComponentType } from 'react';

// User Types
export interface User {
  id: string;
  email: string;
  firstName: string;
  lastName: string;
  phoneNumber?: string;
  dateOfBirth?: string;
  address?: Address;
  role: UserRole;
  isActive: boolean;
  createdAt: string;
  updatedAt: string;
}

export interface Address {
  id: string;
  street: string;
  city: string;
  state: string;
  zipCode: string;
  country: string;
}

export enum UserRole {
  USER = 'USER',
  ADMIN = 'ADMIN',
  ORGANIZER = 'ORGANIZER',
}

// Event Types
export interface Event {
  id: string;
  title: string;
  description: string;
  category: EventCategory;
  venue: Venue;
  organizer: Organizer;
  startDate: string;
  endDate: string;
  status: EventStatus;
  capacity: number;
  availableSeats: number;
  ticketPrice: number;
  currency: string;
  images: string[];
  tags: string[];
  createdAt: string;
  updatedAt: string;
}

export interface Venue {
  id: string;
  name: string;
  address: Address;
  capacity: number;
  amenities: string[];
  images: string[];
}

export interface Organizer {
  id: string;
  name: string;
  description: string;
  contactEmail: string;
  contactPhone: string;
  website?: string;
  logo?: string;
}

export enum EventCategory {
  CONCERT = 'CONCERT',
  CONFERENCE = 'CONFERENCE',
  SPORTS = 'SPORTS',
  THEATER = 'THEATER',
  FESTIVAL = 'FESTIVAL',
  WORKSHOP = 'WORKSHOP',
  EXHIBITION = 'EXHIBITION',
  OTHER = 'OTHER',
}

export enum EventStatus {
  DRAFT = 'DRAFT',
  PUBLISHED = 'PUBLISHED',
  CANCELLED = 'CANCELLED',
  COMPLETED = 'COMPLETED',
}

// Ticket Types
export interface Ticket {
  id: string;
  event: Event;
  user: User;
  ticketNumber: string;
  seatNumber?: string;
  price: number;
  currency: string;
  status: OrderStatus;
  purchaseDate: string;
  validUntil: string;
  qrCode: string;
}

export enum OrderStatus {
  PENDING = 'PENDING',
  CONFIRMED = 'CONFIRMED',
  CANCELLED = 'CANCELLED',
  REFUNDED = 'REFUNDED',
  EXPIRED = 'EXPIRED',
}

// API Response Types
export interface ApiResponse<T> {
  success: boolean;
  message: string;
  data: T;
  timestamp: string;
  errors?: string[];
}

export interface PaginatedResponse<T> {
  content: T[];
  totalElements: number;
  totalPages: number;
  size: number;
  number: number;
  first: boolean;
  last: boolean;
  numberOfElements: number;
}

// Authentication Types
export interface LoginRequest {
  email: string;
  password: string;
}

export interface RegisterRequest {
  email: string;
  password: string;
  firstName: string;
  lastName: string;
  phoneNumber?: string;
  dateOfBirth?: string;
}

export interface AuthResponse {
  token: string;
  refreshToken: string;
  user: User;
  expiresIn: number;
}

export interface RefreshTokenRequest {
  refreshToken: string;
}

// Form Types
export interface EventFormData {
  title: string;
  description: string;
  category: EventCategory;
  venueId: string;
  startDate: string;
  endDate: string;
  capacity: number;
  ticketPrice: number;
  currency: string;
  images: File[];
  tags: string[];
}

export interface UserProfileFormData {
  firstName: string;
  lastName: string;
  phoneNumber: string;
  dateOfBirth: string;
  address: {
    street: string;
    city: string;
    state: string;
    zipCode: string;
    country: string;
  };
}

// Search and Filter Types
export interface EventSearchParams {
  query?: string;
  category?: EventCategory;
  startDate?: string;
  endDate?: string;
  minPrice?: number;
  maxPrice?: number;
  location?: string;
  page?: number;
  size?: number;
  sortBy?: string;
  sortOrder?: 'asc' | 'desc';
}

// UI Types
export interface Toast {
  id: string;
  type: 'success' | 'error' | 'warning' | 'info';
  title: string;
  message?: string;
  duration?: number;
}

export interface Modal {
  id: string;
  isOpen: boolean;
  component: ComponentType<any>;
  props?: Record<string, any>;
}

// Navigation Types
export interface NavItem {
  label: string;
  href: string;
  icon?: ComponentType<any>;
  children?: NavItem[];
  requiresAuth?: boolean;
  roles?: UserRole[];
}

// Chart Types
export interface ChartData {
  label: string;
  value: number;
  color?: string;
}

export interface EventStats {
  totalEvents: number;
  totalTickets: number;
  totalRevenue: number;
  eventsByCategory: ChartData[];
  revenueByMonth: ChartData[];
}

// File Upload Types
export interface FileUpload {
  id: string;
  file: File;
  progress: number;
  status: 'uploading' | 'success' | 'error';
  url?: string;
  error?: string;
} 