const HomePage = () => {
  return (
    <div className="text-center">
      <h1 className="text-4xl font-bold text-gray-900 dark:text-white mb-4">
        Welcome to Event Ticket Platform
      </h1>
      <p className="text-lg text-gray-600 dark:text-gray-400 mb-8">
        Discover and book amazing events with ease
      </p>
      <div className="space-x-4">
        <a
          href="/events"
          className="inline-flex items-center px-6 py-3 border border-transparent text-base font-medium rounded-md shadow-sm text-white bg-primary-600 hover:bg-primary-700"
        >
          Browse Events
        </a>
        <a
          href="/login"
          className="inline-flex items-center px-6 py-3 border border-gray-300 text-base font-medium rounded-md text-gray-700 bg-white hover:bg-gray-50 dark:border-gray-600 dark:text-gray-300 dark:bg-gray-800 dark:hover:bg-gray-700"
        >
          Sign In
        </a>
      </div>
    </div>
  );
};

export default HomePage; 