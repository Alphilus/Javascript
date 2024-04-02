const breedList = document.getElementById('breed-list');
const subBreedList = document.getElementById('sub-breed-list');
const randomSubBreedImageContainer = document.getElementById('random-sub-breed-image');

// Task 1: Get main breed list and display
async function getMainBreeds() {
    try {
        const response = await axios.get('https://dog.ceo/api/breeds/list/all');
        const breeds = response.data.message;
        renderBreeds(breeds);
    } catch (error) {
        console.error('Error fetching main breeds:', error);
    }
}

// Render main breeds into select element
function renderBreeds(breeds) {
    for (let breed in breeds) {
        const option = document.createElement('option');
        option.value = breed;
        option.textContent = breed;
        breedList.appendChild(option);
    }
}

getMainBreeds();

// Task 2: Get sub breeds for selected main breed
async function getSubBreeds() {
    const mainBreed = breedList.value;
    try {
        const response = await axios.get(`https://dog.ceo/api/breed/${mainBreed}/list`);
        const subBreeds = response.data.message;
        renderSubBreeds(subBreeds);
    } catch (error) {
        console.error('Error fetching sub breeds:', error);
    }
}

// Render sub breeds into select element
function renderSubBreeds(subBreeds) {
    subBreedList.innerHTML = '';
    if(subBreeds.length ===  0) {
        const listItem  = document.createElement('li');
        listItem.textContent = 'Không có Sub Breed';
        subBreedList.appendChild(listItem);
    } else {
        subBreeds.forEach(subBreed => {
            const listItem = document.createElement('li');
            listItem.textContent = subBreed;
            listItem.addEventListener('click', () => getRandomSubBreedImage(subBreed));
            listItem.style.cursor = 'pointer';
            subBreedList.appendChild(listItem);
        });
    }
}

// Task 3: Get random image for selected sub breed
async function getRandomSubBreedImage(subBreed) {
    const mainBreed = breedList.value;
    try {
        const response = await axios.get(`https://dog.ceo/api/breed/${mainBreed}/${subBreed}/images/random`);
        const imageUrl = response.data.message;
        renderRandomSubBreedImage(imageUrl);
    } catch (error) {
        console.error('Error fetching random sub breed image:', error);
    }
}

// Render random sub breed image
function renderRandomSubBreedImage(imageUrl) {
    randomSubBreedImageContainer.innerHTML = '';
    const image = document.createElement('img');
    image.src = imageUrl;
    image.alt = 'Random Sub Breed Image';
    randomSubBreedImageContainer.appendChild(image);
}
