import React, { useState, useEffect } from 'react';
import {
  View,
  Text,
  StyleSheet,
  TouchableOpacity,
  SafeAreaView,
  StatusBar,
  ScrollView,
  Dimensions,
  Modal,
  TextInput,
} from 'react-native';
import { useNavigation } from '@react-navigation/native';

const { width, height } = Dimensions.get('window');

interface Prayer {
  id: string;
  title: string;
  content: string;
  reference: string;
}

interface BibleVerse {
  book: string;
  chapter: number;
  verse: number;
  text: string;
}

const PrayerTime: React.FC = () => {
  const navigation = useNavigation();
  const [isPlaying, setIsPlaying] = useState(false);
  const [currentPrayer, setCurrentPrayer] = useState<Prayer | null>(null);
  const [bibleModalVisible, setBibleModalVisible] = useState(false);
  const [searchQuery, setSearchQuery] = useState('');
  const [selectedBook, setSelectedBook] = useState('John');
  const [selectedChapter, setSelectedChapter] = useState(3);
  const [selectedVerse, setSelectedVerse] = useState(16);

  // Sample prayer data
  const prayers: Prayer[] = [
    {
      id: '1',
      title: 'The Lord\'s Prayer',
      content: 'Our Father in heaven, hallowed be your name. Your kingdom come, your will be done, on earth as it is in heaven. Give us this day our daily bread, and forgive us our debts, as we also have forgiven our debtors. And lead us not into temptation, but deliver us from evil.',
      reference: 'Matthew 6:9-13'
    },
    {
      id: '2',
      title: 'Psalm 23',
      content: 'The Lord is my shepherd, I shall not want. He makes me lie down in green pastures, he leads me beside quiet waters, he refreshes my soul. He guides me along the right paths for his name\'s sake. Even though I walk through the darkest valley, I will fear no evil, for you are with me; your rod and your staff, they comfort me.',
      reference: 'Psalm 23:1-4'
    },
    {
      id: '3',
      title: 'Prayer of Jabez',
      content: 'Oh, that you would bless me and enlarge my territory! Let your hand be with me, and keep me from harm so that I will be free from pain.',
      reference: '1 Chronicles 4:10'
    }
  ];

  useEffect(() => {
    // Set a random prayer on component mount
    const randomPrayer = prayers[Math.floor(Math.random() * prayers.length)];
    setCurrentPrayer(randomPrayer);
  }, []);

  const togglePlayPause = () => {
    setIsPlaying(!isPlaying);
    // Here you would integrate with your actual audio streaming service
    console.log(isPlaying ? 'Pausing audio' : 'Playing audio');
  };

  const handleNavigation = (destination: string) => {
    console.log(`Navigating to ${destination}`);
    if (destination === 'Home') {
      navigation.navigate('Home' as never);
    } else if (destination === 'Prayer List') {
      navigation.navigate('PrayerList' as never);
    }
    // Add your navigation logic here for other destinations
  };

  const openBibleApp = () => {
    setBibleModalVisible(true);
  };

  const closeBibleApp = () => {
    setBibleModalVisible(false);
  };

  // Sample Bible verses
  const sampleVerses: BibleVerse[] = [
    {
      book: 'John',
      chapter: 3,
      verse: 16,
      text: 'For God so loved the world that he gave his one and only Son, that whoever believes in him shall not perish but have eternal life.'
    },
    {
      book: 'Psalm',
      chapter: 23,
      verse: 1,
      text: 'The Lord is my shepherd, I shall not want.'
    },
    {
      book: 'Matthew',
      chapter: 6,
      verse: 9,
      text: 'This, then, is how you should pray: "Our Father in heaven, hallowed be your name."'
    },
    {
      book: 'Philippians',
      chapter: 4,
      verse: 6,
      text: 'Do not be anxious about anything, but in every situation, by prayer and petition, with thanksgiving, present your requests to God.'
    }
  ];

  const filteredVerses = sampleVerses.filter(verse =>
    verse.text.toLowerCase().includes(searchQuery.toLowerCase()) ||
    `${verse.book} ${verse.chapter}:${verse.verse}`.toLowerCase().includes(searchQuery.toLowerCase())
  );

  return (
    <SafeAreaView style={styles.container}>
      <StatusBar barStyle="dark-content" backgroundColor="#f8f9fa" />
      
      {/* Audio Player Section */}
      <View style={styles.audioPlayerContainer}>
        <View style={styles.audioPlayer}>
          <TouchableOpacity
            style={styles.playButton}
            onPress={togglePlayPause}
          >
            <Text style={styles.playButtonText}>
              {isPlaying ? '⏸️' : '▶️'}
            </Text>
          </TouchableOpacity>
          <View style={styles.audioInfo}>
            <Text style={styles.audioTitle}>Prayer Time Stream</Text>
            <Text style={styles.audioStatus}>
              {isPlaying ? 'Now Playing' : 'Paused'}
            </Text>
          </View>
        </View>
      </View>

      {/* Prayer Content Section */}
      <ScrollView style={styles.contentContainer} showsVerticalScrollIndicator={false}>
        {currentPrayer && (
          <View style={styles.prayerContainer}>
            <Text style={styles.prayerTitle}>{currentPrayer.title}</Text>
            <Text style={styles.prayerReference}>{currentPrayer.reference}</Text>
            <View style={styles.prayerTextContainer}>
              <Text style={styles.prayerText}>{currentPrayer.content}</Text>
            </View>
          </View>
        )}
      </ScrollView>

      {/* Navigation Buttons */}
      <View style={styles.navigationContainer}>
        <TouchableOpacity
          style={[styles.navButton, styles.leftButton]}
          onPress={() => handleNavigation('Prayer List')}
        >
          <Text style={styles.navButtonText}>Prayer List</Text>
        </TouchableOpacity>

        <TouchableOpacity
          style={[styles.navButton, styles.centerButton]}
          onPress={() => handleNavigation('Home')}
        >
          <Text style={styles.navButtonText}>Home</Text>
        </TouchableOpacity>

        <TouchableOpacity
          style={[styles.navButton, styles.rightButton]}
          onPress={openBibleApp}
        >
          <Text style={styles.navButtonText}>Bible</Text>
        </TouchableOpacity>
      </View>

      {/* Bible App Modal */}
      <Modal
        visible={bibleModalVisible}
        animationType="slide"
        transparent={true}
        onRequestClose={closeBibleApp}
      >
        <View style={styles.modalOverlay}>
          <View style={styles.bibleModal}>
            <View style={styles.bibleHeader}>
              <Text style={styles.bibleTitle}>Bible App</Text>
              <TouchableOpacity
                style={styles.closeButton}
                onPress={closeBibleApp}
              >
                <Text style={styles.closeButtonText}>×</Text>
              </TouchableOpacity>
            </View>

            <View style={styles.searchContainer}>
              <TextInput
                style={styles.searchInput}
                placeholder="Search verses..."
                value={searchQuery}
                onChangeText={setSearchQuery}
              />
            </View>

            <ScrollView style={styles.versesContainer} showsVerticalScrollIndicator={false}>
              {filteredVerses.map((verse, index) => (
                <View key={index} style={styles.verseCard}>
                  <View style={styles.verseHeader}>
                    <Text style={styles.verseReference}>
                      {verse.book} {verse.chapter}:{verse.verse}
                    </Text>
                    <TouchableOpacity style={styles.bookmarkButton}>
                      <Text style={styles.bookmarkIcon}>🔖</Text>
                    </TouchableOpacity>
                  </View>
                  <Text style={styles.verseText}>{verse.text}</Text>
                </View>
              ))}
            </ScrollView>

            <View style={styles.bibleFooter}>
              <TouchableOpacity style={styles.bibleActionButton}>
                <Text style={styles.bibleActionText}>Share Verse</Text>
              </TouchableOpacity>
              <TouchableOpacity style={styles.bibleActionButton}>
                <Text style={styles.bibleActionText}>Add to Notes</Text>
              </TouchableOpacity>
            </View>
          </View>
        </View>
      </Modal>
    </SafeAreaView>
  );
};

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: '#D9BEF4',
  },
  audioPlayerContainer: {
    paddingTop: 20,
    paddingHorizontal: 20,
    paddingBottom: 30,
  },
  audioPlayer: {
    backgroundColor: '#FFFFFF',
    borderRadius: 15,
    padding: 20,
    flexDirection: 'row',
    alignItems: 'center',
    elevation: 3,
    shadowColor: '#000',
    shadowOffset: {
      width: 0,
      height: 2,
    },
    shadowOpacity: 0.25,
    shadowRadius: 3.84,
  },
  playButton: {
    width: 56,
    height: 56,
    borderRadius: 28,
    backgroundColor: '#FFD700',
    justifyContent: 'center',
    alignItems: 'center',
    marginRight: 15,
  },
  playButtonText: {
    fontSize: 24,
  },
  audioInfo: {
    flex: 1,
  },
  audioTitle: {
    fontSize: 16,
    fontWeight: 'bold',
    color: '#1a1a1a',
    marginBottom: 5,
    fontFamily: 'sans-serif',
  },
  audioStatus: {
    fontSize: 14,
    color: '#6c757d',
    fontFamily: 'sans-serif',
  },
  contentContainer: {
    flex: 1,
    paddingHorizontal: 20,
  },
  prayerContainer: {
    backgroundColor: '#FFFFFF',
    borderRadius: 15,
    padding: 25,
    marginBottom: 20,
    elevation: 2,
    shadowColor: '#000',
    shadowOffset: {
      width: 0,
      height: 1,
    },
    shadowOpacity: 0.22,
    shadowRadius: 2.22,
  },
  prayerTitle: {
    fontSize: 22,
    fontWeight: 'bold',
    color: '#1a1a1a',
    marginBottom: 8,
    textAlign: 'center',
    fontFamily: 'sans-serif',
  },
  prayerReference: {
    fontSize: 16,
    color: '#FFD700',
    marginBottom: 20,
    textAlign: 'center',
    fontStyle: 'italic',
    fontFamily: 'sans-serif',
  },
  prayerTextContainer: {
    backgroundColor: '#FFFFFF',
    borderRadius: 10,
    padding: 20,
    borderLeftWidth: 4,
    borderLeftColor: '#FFD700',
  },
  prayerText: {
    fontSize: 16,
    lineHeight: 24,
    color: '#495057',
    textAlign: 'justify',
    fontFamily: 'serif',
    fontStyle: 'italic',
  },
  navigationContainer: {
    flexDirection: 'row',
    justifyContent: 'space-between',
    paddingHorizontal: 20,
    paddingBottom: 30,
    paddingTop: 20,
  },
  navButton: {
    backgroundColor: '#FFD700',
    paddingHorizontal: 10,
    paddingVertical: 13,
    borderRadius: 22,
    elevation: 2,
    shadowColor: '#000',
    shadowOffset: {
      width: 0,
      height: 1,
    },
    shadowOpacity: 0.2,
    shadowRadius: 2.5,
  },
  leftButton: {
    flex: 0.3,
  },
  centerButton: {
    flex: 0.3,
  },
  rightButton: {
    flex: 0.3,
  },
  navButtonText: {
    color: '#000000',
    fontSize: 14,
    fontWeight: '600',
    textAlign: 'center',
    fontFamily: 'sans-serif',
  },
  modalOverlay: {
    flex: 1,
    backgroundColor: 'rgba(0, 0, 0, 0.5)',
    justifyContent: 'center',
    alignItems: 'center',
  },
  bibleModal: {
    backgroundColor: '#FFFFFF',
    borderRadius: 20,
    width: '90%',
    height: '80%',
    elevation: 5,
    shadowColor: '#000',
    shadowOffset: {
      width: 0,
      height: 2,
    },
    shadowOpacity: 0.25,
    shadowRadius: 3.84,
  },
  bibleHeader: {
    flexDirection: 'row',
    justifyContent: 'space-between',
    alignItems: 'center',
    padding: 20,
    borderBottomWidth: 1,
    borderBottomColor: '#e9ecef',
  },
  bibleTitle: {
    fontSize: 24,
    fontWeight: 'bold',
    color: '#1a1a1a',
    fontFamily: 'sans-serif',
  },
  closeButton: {
    width: 30,
    height: 30,
    borderRadius: 15,
    backgroundColor: '#FFD700',
    justifyContent: 'center',
    alignItems: 'center',
  },
  closeButtonText: {
    fontSize: 20,
    fontWeight: 'bold',
    color: '#000000',
    fontFamily: 'sans-serif',
  },
  searchContainer: {
    padding: 20,
    paddingTop: 10,
  },
  searchInput: {
    backgroundColor: '#f8f9fa',
    borderRadius: 10,
    padding: 12,
    fontSize: 16,
    borderWidth: 1,
    borderColor: '#FFD700',
    fontFamily: 'sans-serif',
  },
  versesContainer: {
    flex: 1,
    paddingHorizontal: 20,
  },
  verseCard: {
    backgroundColor: '#f8f9fa',
    borderRadius: 10,
    padding: 15,
    marginBottom: 15,
    borderLeftWidth: 4,
    borderLeftColor: '#FFD700',
  },
  verseHeader: {
    flexDirection: 'row',
    justifyContent: 'space-between',
    alignItems: 'center',
    marginBottom: 10,
  },
  verseReference: {
    fontSize: 16,
    fontWeight: 'bold',
    color: '#1a1a1a',
    fontFamily: 'sans-serif',
  },
  bookmarkButton: {
    padding: 5,
  },
  bookmarkIcon: {
    fontSize: 20,
  },
  verseText: {
    fontSize: 14,
    color: '#495057',
    lineHeight: 20,
    fontFamily: 'sans-serif',
  },
  bibleFooter: {
    flexDirection: 'row',
    justifyContent: 'space-around',
    padding: 20,
    borderTopWidth: 1,
    borderTopColor: '#e9ecef',
  },
  bibleActionButton: {
    backgroundColor: '#FFD700',
    paddingHorizontal: 20,
    paddingVertical: 10,
    borderRadius: 20,
  },
  bibleActionText: {
    color: '#000000',
    fontSize: 14,
    fontWeight: '600',
    fontFamily: 'sans-serif',
  },
});

export default PrayerTime;
