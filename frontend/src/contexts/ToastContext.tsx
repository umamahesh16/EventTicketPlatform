import { createContext, useContext, ReactNode } from 'react';

interface ToastContextType {
  showToast: () => void;
}

const ToastContext = createContext<ToastContextType | undefined>(undefined);

export const useToast = () => {
  const context = useContext(ToastContext);
  if (context === undefined) {
    throw new Error('useToast must be used within a ToastProvider');
  }
  return context;
};

interface ToastProviderProps {
  children: ReactNode;
}

export const ToastProvider = ({ children }: ToastProviderProps) => {
  const value = {
    showToast: () => {},
  };

  return <ToastContext.Provider value={value}>{children}</ToastContext.Provider>;
}; 