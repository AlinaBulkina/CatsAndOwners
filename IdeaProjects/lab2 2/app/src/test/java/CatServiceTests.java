import static org.mockito.ArgumentMatchers.any;

class CatServiceTests {
/*
    @Mock
    private ICatRepo catRepo;

    @Mock
    private IOwnerRepo ownerRepo;

    @InjectMocks
    private CatService catService;

    @InjectMocks
    private OwnerService ownerService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createCatShouldReturnValidDto() {
        int ownerId = 1;
        Owner testOwner = new Owner("TestOwner", LocalDate.of(2024, 10, 10), new ArrayList<>());
        testOwner.setId(ownerId);
        Cat testCat = new Cat("TestCat", LocalDate.of(2024, 10, 10), Breed.Unknown, Color.Unknown, testOwner, new HashSet<>());
        CatDto expectedDto = CatMapper.asDto(testCat);

        when(ownerService.getOwnerById(ownerId));

        CatDto result = catService.createCat("Tom", LocalDate.of(2012, 7, 15), "Siamese", "White", ownerId);

        // Assert
        verify(ownerService).getOwnerById(ownerId);
        assertEquals(expectedDto, result);
    }

    @Test
    void unfriendCatsShouldRemoveFriendships() {
        // Arrange
        int ownerId = 1;
        Owner testOwner = new Owner("TestOwner", LocalDate.of(2024, 10, 10), new ArrayList<>());
        testOwner.setId(ownerId);
        Cat testCat1 = new Cat("TestCat1", LocalDate.of(2024, 10, 10), Breed.Unknown, Color.Unknown, testOwner, new HashSet<>());
        Cat testCat2 = new Cat("TestCat2", LocalDate.of(2024, 10, 10), Breed.Unknown, Color.Unknown, testOwner, new HashSet<>());

        testCat1.addFriend(testCat1);
        testCat2.addFriend(testCat2);

        int catId1 = 1;
        int catId2 = 2;

        testCat1.setId(catId1);
        testCat2.setId(catId2);

        // Act
        catService.unfriendCats(catId1, catId2);

        // Assert
        assertFalse(testCat1.getFriends().contains(testCat2));
        assertFalse(testCat1.getFriends().contains(testCat2));
    }

    @Test
    void removeCatShouldRemoveCatAndItsRelationships() {
        int catIdToRemove = 1;
        int ownerId = 1;
        Owner testOwner = new Owner("TestOwner", LocalDate.of(2024, 10, 10), new ArrayList<>());
        testOwner.setId(ownerId);
        Cat friendCat =  new Cat("TestCat1", LocalDate.of(2024, 10, 10), Breed.Unknown, Color.Unknown, testOwner, new HashSet<>());
        testOwner.addCat(friendCat);

        friendCat.setId(catIdToRemove);
        friendCat.setOwner(testOwner);
        friendCat.setFriends(new HashSet<>(Collections.singletonList(friendCat)));

        when(catService.getCatById(catIdToRemove));

        catService.removeCat(catIdToRemove);
    }
    */

}
