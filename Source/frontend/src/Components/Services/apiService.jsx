const API_BASE_URL = 'http://localhost:8080/';

export const postData = async (data) => {
  try {
    const response = await fetch(`${API_BASE_URL}/data`, {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify(data),
    });
    if (!response.ok) throw new Error('Network response was not ok');
    return await response.json();
  } catch (error) {
    console.error("Error posting data:", error);
    throw error;
  }
};
