# AMap
## CS3216 Assignment 3 Project


### Zhu Liang (A0093910H)
- Front-end Development (Google Maps Integration, APIs)
- Admin

### Heng Rui Yan Ryan (A0108233R)
- Front-end Development (Ionic Framework, Ionic UI)
- Floormap Image Creation

### Varun Patro (A0131729E)
- Back-end Development
- Google Maps Overlay Tiles Generation
- Database

## Dinh Viet Thang (A0126513N)
- Back-end Development
- Database

#### Public API

- [Get all categories](#get-all-categories)
- [Get locations by category](#get-locations-by-category)
- [Facebook authentication](#facebook-authentication)
- [Facebook authentication callback](#facebook-authentication-callback)

#### API

- [Logout](#logout)
- [Get user profile](#get-user-profile)
- [Get user’s bookmarks](#get-users-bookmarks)
- [Create new bookmark](#create-new-bookmark)
- [Delete bookmark](#delete-bookmark)
- [Get locations by location id](#get-locations-by-location-id)
- [Add new locations to the map](#add-new-locations-to-the-map)
- [Update existing location](#update-existing-location)
- [Delete location](#delete-location)

# **Get all categories**
----

* **URL**

  `GET`  /api/category

*  **URL Params**

* **Data Params**

* **Success Response:**

  * **Code:** 200 <br />

    **Content:**
    ```json
    {
        venues: [{
            name: 'Lecture Theatre',
            catid: '1'
        }, {
            name: 'Tutorial Room',
            catid: '2'
        }, {
            name: 'Laboratory',
            catid: '3'
        }],
        utilities: [{
            name: 'Vending Machine',
            catid: '31'
        }, {
            name: 'Water Cooler',
            catid: '32'
        }, {
            name: 'Toilet',
            catid: '33'
        }, {
            name: 'ATM',
            catid: '34'
        }]
    };
    ```

* **Error Response:**

  * **Code:** 400 <br />

    **Content:** ``

* **Sample Call:**

# **Get locations by category**
----

* **URL**

  `GET`  /api/category/:catid

*  **URL Params**

   **Required:**

   `catid=[integer]`

   **Optional:**

* **Data Params**

* **Success Response:**

  * **Code:** 200 <br />

    **Content:**
    ```json
    [ {
        "id": int,
        "name": string,
        "catid": int,
        "position": {
            "lat": float,
            "lng": float
        }
      },
      ...
    ]
    ```

* **Error Response:**

  * **Code:** 400 <br />

    **Content:** ``

* **Sample Call:**

# **Facebook authentication**
----

* **URL**

  `GET`  /auth/facebook

*  **URL Params**

* **Data Params**

* **Success Response:**

  * **Code:** 302 <br />

    **Content:** `redirect to /auth/facebook/callback`

* **Error Response:**

  * **Code:** 400 <br />

    **Content:** ``

* **Sample Call:**

# **Facebook authentication callback**
----

* **URL**

  `GET`  /auth/facebook

*  **URL Params**

    Facebook login params

* **Data Params**

* **Success Response:**

  * **Code:** 302 <br />

    **Content:** `login success`

* **Error Response:**

  * **Code:** 400 <br />

    **Content:** ``

* **Sample Call:**

# **Logout**
----

* **URL**

  `GET`  /auth/logout

*  **URL Params**

* **Data Params**

* **Success Response:**

  * **Code:** 302 <br />

    **Content:** `logout success and redirect to homepage`

* **Error Response:**

  * **Code:** 400 <br />

    **Content:** `error`

* **Sample Call:**

# **Get user profile**
----

* **URL**

  `GET`  /api/user/profile

*  **URL Params**

* **Data Params**

* **Success Response:**

  * **Code:** 200 <br />

    **Content:**
    ```json
    {
        "id":[int],
        "name":[string],
        "facebook_token":[string],
        "createdAt":[date],
        "updatedAt":[date]
    }
    ```

* **Error Response:**

  * **Code:** 401 <br />

    **Content:** `auth error`

* **Sample Call:**

# **Get user's bookmarks**
----

* **URL**

  `GET`  /api/user/bookmarks

*  **URL Params**

* **Data Params**

* **Success Response:**

  * **Code:** 200 <br />

    **Content:**
    ```json
    [
        {
            "id":[int],
            "note":[string],
            "createdAt":[date],
            "updatedAt":[date],
            "UserId":[integer],
            "LocationId":[integer],
            "Location":{
                "id": [int],
                "name": [string],
                "catid": [int],
                "lat": [float],
                "lng": [float],
                "imageUrl": [string],
                "createdAt":[date],
                "updatedAt":[date]
            }
        },
        ...
    ]
    ```

* **Error Response:**

    *   **Code:** 400 <br />

        **Content:** `error`

    *   **Code:** 401 <br />

        **Content:** `auth error`

* **Sample Call:**



* **Error Response:**

    *   **Code:** 400 <br />

        **Content:** ``

    *   **Code:** 401 <br />

        **Content:** `auth error`

* **Sample Call:**

    curl -XGET localhost:3000/api/location?id=7

# **Create new bookmark**
----

* **URL**

  `POST`  /api/bookmark

*  **URL Params**

* **Data Params**

    **Required:**

    `locationId=[integer]`

    `note=[string]`

* **Success Response:**

    * **Code:** 200 <br />

    **Content:**

* **Error Response:**

    *   **Code:** 400 <br />

        **Content:** ``

    *   **Code:** 401 <br />

        **Content:** `auth error`

* **Sample Call:**

    curl -XPOST localhost:3000/api/bookmark -d
    ```json
    {
        "locationId": 7,
        "note": "cs3216 lecture room"
    }
    ```

# **Delete bookmark**
----

* **URL**

  `DELETE`  /api/bookmark/:id

*  **URL Params**

    **Required:**

    `id=[integer]`

* **Data Params**

* **Success Response:**

    * **Code:** 200 <br />

    **Content:** `Number of bookmark affected (which is always 1)`

* **Error Response:**

    *   **Code:** 400 <br />

        **Content:** `Bookmark does not exist or not belong to the user`

    *   **Code:** 401 <br />

        **Content:** `auth error`

* **Sample Call:**

    curl -XDELETE localhost:3000/api/bookmark/32


# **Get locations by location id**
----

* **URL**

  `GET`  /api/location

*  **URL Params**

   **Required:**

   **Optional:**

* **Query string**

    **Required:**

    `id=[integer]`

* **Success Response:**

  * **Code:** 200 <br />

    **Content:**
    ```json
    {
        "id": int,
        "name": string,
        "catid": int,
        "position": {
            "lat": float,
            "lng": float
        }
    }
    ```

# **Add new locations to the map**
----

* **URL**

  `POST`  /api/location

*  **Data Params**

    ```json
    [
        {
            "name": string,
            "catid": int,
            "lat": float,
            "lng": float
        },
        ...
    ]
    ```

* **Query string**

* **Success Response:**

  * **Code:** 200 <br />

    **Content:**
    ```json
    {
        "id": int,
        "name": string,
        "catid": int,
        "position": {
            "lat": float,
            "lng": float
        },
        "imageUrl": string
    }
    ```
* **Error Response:**

    *   **Code:** 400 <br />

        **Content:**

* **Sample Call:**

    curl -XPOST localhost:3000/api/location -d
    ```json
    [{
        "name": "test 1",
        "lat": 1.29493,
        "lng": 103.77254,
        "catid": 34
    },
    {
        "name": "test 2",
        "lat": 1.29487,
        "lng": 103.77256,
        "catid": 34
    },
    {
        "name": "test 3",
        "lat": 1.29487,
        "lng": 103.77375,
        "catid": 32
    }]
    ```

# **Update existing location**
----

* **URL**

  `PUT`  /api/location

*  **Data Params**

    ```json
    [
        {
            "id": int,
            "name": string,
            "catid": int,
            "lat": float,
            "lng": float
        },
        ...
    ]
    ```

* **Query string**

* **Success Response:**

  * **Code:** 200 <br />

    **Content:**


* **Error Response:**

  * **Code:** 400 <br />

    **Content:** `Error`

* **Sample Call:**

    curl -XPUT localhost:3000/api/location -d
    ```json
    {
        "name": "test 1",
        "lat": 1.29493,
        "lng": 103.77254,
        "catid": 34
    }
    ```

# **Delete location**
----

* **URL**

  `DELETE`  /api/location

*  **Data Params**

    ```json
        {
            "id": int
        }
    ```

* **Query string**

* **Success Response:**

  * **Code:** 200 <br />

    **Content:** ``


* **Error Response:**

  * **Code:** 400 <br />

    **Content:** `Error`

* **Sample Call:**

    curl -XDELETE localhost:3000/api/location -d
    ```json
    {
        "name": "test 1"
    }
    ```
