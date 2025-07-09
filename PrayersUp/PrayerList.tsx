import React, { useState, useEffect } from 'react';
import {
  View,
  Text,
  StyleSheet,
  TouchableOpacity,
  SafeAreaView,
  StatusBar,
  ScrollView,
  TextInput,
  Alert,
} from 'react-native';
import { useNavigation } from '@react-navigation/native';

interface PrayerItem {
  id: string;
  text: string;
  isChecked: boolean;
  timestamp: string;
}

const PrayerList: React.FC = () => {
  const navigation = useNavigation();
  const [prayerItems, setPrayerItems] = useState<PrayerItem[]>([]);
  const [newPrayerText, setNewPrayerText] = useState('');
  const [isAddingPrayer, setIsAddingPrayer] = useState(false);

  // Load sample prayer items on component mount
  useEffect(() => {
    const samplePrayers: PrayerItem[] = [
      {
        id: '1',
        text: 'Pray for healing for my grandmother',
        isChecked: false,
        timestamp: new Date().toLocaleDateString(),
      },
      {
        id: '2',
        text: 'Pray for wisdom in making important decisions',
        isChecked: true,
        timestamp: new Date().toLocaleDateString(),
      },
      {
        id: '3',
        text: 'Pray for peace in our community',
        isChecked: false,
        timestamp: new Date().toLocaleDateString(),
      },
      {
        id: '4',
        text: 'Pray for strength during difficult times',
        isChecked: false,
        timestamp: new Date().toLocaleDateString(),
      },
    ];
    setPrayerItems(samplePrayers);
  }, []);

  const togglePrayerItem = (id: string) => {
    setPrayerItems(prevItems =>
      prevItems.map(item =>
        item.id === id ? { ...item, isChecked: !item.isChecked } : item
      )
    );
  };

  const addPrayerItem = () => {
    if (newPrayerText.trim()) {
      const newPrayer: PrayerItem = {
        id: Date.now().toString(),
        text: newPrayerText.trim(),
        isChecked: false,
        timestamp: new Date().toLocaleDateString(),
      };
      setPrayerItems(prevItems => [newPrayer, ...prevItems]);
      setNewPrayerText('');
      setIsAddingPrayer(false);
    }
  };

  const deletePrayerItem = (id: string) => {
    Alert.alert(
      'Delete Prayer',
      'Are you sure you want to delete this prayer?',
      [
        { text: 'Cancel', style: 'cancel' },
        {
          text: 'Delete',
          style: 'destructive',
          onPress: () => {
            setPrayerItems(prevItems => prevItems.filter(item => item.id !== id));
          },
        },
      ]
    );
  };

  const handleNavigation = (destination: string) => {
    console.log(`Navigating to ${destination}`);
    if (destination === 'Home') {
      navigation.navigate('Home' as never);
    } else if (destination === 'PrayerTime') {
      navigation.navigate('PrayerTime' as never);
    }
  };

  const checkedCount = prayerItems.filter(item => item.isChecked).length;
  const totalCount = prayerItems.length;

  return (
    <SafeAreaView style={styles.container}>
      <StatusBar barStyle="dark-content" backgroundColor="#f8f9fa" />
      
      {/* Header */}
      <View style={styles.header}>
        <Text style={styles.headerTitle}>Prayer List</Text>
        <Text style={styles.headerSubtitle}>
          {checkedCount} of {totalCount} prayers completed
        </Text>
      </View>

      {/* Add Prayer Section */}
      <View style={styles.addSection}>
        {isAddingPrayer ? (
          <View style={styles.addPrayerContainer}>
            <TextInput
              style={styles.textInput}
              placeholder="Enter your prayer request..."
              value={newPrayerText}
              onChangeText={setNewPrayerText}
              multiline
              autoFocus
            />
            <View style={styles.addButtonContainer}>
              <TouchableOpacity
                style={[styles.addButton, styles.cancelButton]}
                onPress={() => {
                  setIsAddingPrayer(false);
                  setNewPrayerText('');
                }}
              >
                <Text style={styles.cancelButtonText}>Cancel</Text>
              </TouchableOpacity>
              <TouchableOpacity
                style={[styles.addButton, styles.saveButton]}
                onPress={addPrayerItem}
              >
                <Text style={styles.saveButtonText}>Add Prayer</Text>
              </TouchableOpacity>
            </View>
          </View>
        ) : (
          <TouchableOpacity
            style={styles.addPrayerButton}
            onPress={() => setIsAddingPrayer(true)}
          >
            <Text style={styles.addPrayerButtonText}>+ Add New Prayer</Text>
          </TouchableOpacity>
        )}
      </View>

      {/* Prayer List */}
      <ScrollView style={styles.prayerList} showsVerticalScrollIndicator={false}>
        {prayerItems.length === 0 ? (
          <View style={styles.emptyState}>
            <Text style={styles.emptyStateText}>No prayers added yet</Text>
            <Text style={styles.emptyStateSubtext}>
              Tap "Add New Prayer" to get started
            </Text>
          </View>
        ) : (
          prayerItems.map(item => (
            <View key={item.id} style={styles.prayerItem}>
              <TouchableOpacity
                style={styles.checkboxContainer}
                onPress={() => togglePrayerItem(item.id)}
              >
                <View style={[styles.checkbox, item.isChecked && styles.checkboxChecked]}>
                  {item.isChecked && <Text style={styles.checkmark}>✓</Text>}
                </View>
              </TouchableOpacity>
              
              <View style={styles.prayerContent}>
                <Text style={[
                  styles.prayerText,
                  item.isChecked && styles.prayerTextChecked
                ]}>
                  {item.text}
                </Text>
                <Text style={styles.prayerTimestamp}>{item.timestamp}</Text>
              </View>
              
              <TouchableOpacity
                style={styles.deleteButton}
                onPress={() => deletePrayerItem(item.id)}
              >
                <Text style={styles.deleteButtonText}>×</Text>
              </TouchableOpacity>
            </View>
          ))
        )}
      </ScrollView>

      {/* Navigation Buttons */}
      <View style={styles.navigationContainer}>
        <TouchableOpacity
          style={[styles.navButton, styles.leftButton]}
          onPress={() => handleNavigation('Home')}
        >
          <Text style={styles.navButtonText}>Home</Text>
        </TouchableOpacity>

        <TouchableOpacity
          style={[styles.navButton, styles.centerButton]}
          onPress={() => handleNavigation('PrayerTime')}
        >
          <Text style={styles.navButtonText}>Prayer Time</Text>
        </TouchableOpacity>
      </View>
    </SafeAreaView>
  );
};

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: '#D9BEF4',
  },
  header: {
    paddingTop: 20,
    paddingHorizontal: 20,
    paddingBottom: 15,
    backgroundColor: '#FFFFFF',
    borderBottomWidth: 1,
    borderBottomColor: '#e9ecef',
  },
  headerTitle: {
    fontSize: 26,
    fontWeight: 'bold',
    color: '#1a1a1a',
    marginBottom: 5,
    fontFamily: 'sans-serif',
  },
  headerSubtitle: {
    fontSize: 16,
    color: '#6c757d',
    fontFamily: 'sans-serif',
  },
  addSection: {
    paddingHorizontal: 20,
    paddingVertical: 15,
    backgroundColor: '#FFFFFF',
    borderBottomWidth: 1,
    borderBottomColor: '#e9ecef',
  },
  addPrayerButton: {
    backgroundColor: '#FFD700',
    paddingVertical: 10,
    paddingHorizontal: 18,
    borderRadius: 22,
    alignItems: 'center',
  },
  addPrayerButtonText: {
    color: '#000000',
    fontSize: 14,
    fontWeight: '600',
    fontFamily: 'sans-serif',
  },
  addPrayerContainer: {
    backgroundColor: '#FFFFFF',
    borderRadius: 10,
    padding: 15,
  },
  textInput: {
    backgroundColor: '#FFFFFF',
    borderRadius: 8,
    padding: 12,
    fontSize: 16,
    minHeight: 80,
    textAlignVertical: 'top',
    borderWidth: 1,
    borderColor: '#FFD700',
    fontFamily: 'sans-serif',
  },
  addButtonContainer: {
    flexDirection: 'row',
    justifyContent: 'space-between',
    marginTop: 15,
  },
  addButton: {
    flex: 0.48,
    paddingVertical: 10,
    borderRadius: 20,
    alignItems: 'center',
  },
  cancelButton: {
    backgroundColor: '#6c757d',
  },
  saveButton: {
    backgroundColor: '#FFD700',
  },
  cancelButtonText: {
    color: '#FFFFFF',
    fontSize: 14,
    fontWeight: '600',
    fontFamily: 'sans-serif',
  },
  saveButtonText: {
    color: '#000000',
    fontSize: 14,
    fontWeight: '600',
    fontFamily: 'sans-serif',
  },
  prayerList: {
    flex: 1,
    paddingHorizontal: 20,
    paddingTop: 15,
  },
  emptyState: {
    flex: 1,
    justifyContent: 'center',
    alignItems: 'center',
    paddingVertical: 50,
  },
  emptyStateText: {
    fontSize: 18,
    color: '#6c757d',
    marginBottom: 8,
    fontFamily: 'sans-serif',
  },
  emptyStateSubtext: {
    fontSize: 14,
    color: '#adb5bd',
    textAlign: 'center',
    fontFamily: 'sans-serif',
  },
  prayerItem: {
    backgroundColor: '#FFFFFF',
    borderRadius: 10,
    padding: 15,
    marginBottom: 10,
    flexDirection: 'row',
    alignItems: 'center',
    elevation: 1,
    shadowColor: '#000',
    shadowOffset: {
      width: 0,
      height: 1,
    },
    shadowOpacity: 0.22,
    shadowRadius: 2.22,
  },
  checkboxContainer: {
    marginRight: 15,
  },
  checkbox: {
    width: 24,
    height: 24,
    borderRadius: 12,
    borderWidth: 2,
    borderColor: '#FFD700',
    justifyContent: 'center',
    alignItems: 'center',
    backgroundColor: '#FFFFFF',
  },
  checkboxChecked: {
    backgroundColor: '#FFD700',
  },
  checkmark: {
    color: '#FFFFFF',
    fontSize: 16,
    fontWeight: 'bold',
  },
  prayerContent: {
    flex: 1,
  },
  prayerText: {
    fontSize: 14,
    color: '#1a1a1a',
    lineHeight: 20,
    marginBottom: 5,
    fontFamily: 'sans-serif',
  },
  prayerTextChecked: {
    textDecorationLine: 'line-through',
    color: '#6c757d',
    fontFamily: 'sans-serif',
  },
  prayerTimestamp: {
    fontSize: 12,
    color: '#adb5bd',
    fontFamily: 'sans-serif',
  },
  deleteButton: {
    width: 30,
    height: 30,
    borderRadius: 15,
    backgroundColor: '#dc3545',
    justifyContent: 'center',
    alignItems: 'center',
    marginLeft: 10,
  },
  deleteButtonText: {
    color: '#FFFFFF',
    fontSize: 18,
    fontWeight: 'bold',
    fontFamily: 'sans-serif',
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
    paddingHorizontal: 23,
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
    flex: 0.48,
  },
  centerButton: {
    flex: 0.48,
  },
  navButtonText: {
    color: '#000000',
    fontSize: 14,
    fontWeight: '600',
    textAlign: 'center',
    fontFamily: 'sans-serif',
  },
});

export default PrayerList;
