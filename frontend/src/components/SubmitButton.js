import React, {Component} from 'react';
import {
  StyleSheet,
  Button,
  View,
  Text,
  Alert,
} from 'react-native';
import Constants from 'expo-constants';

export default class Submit extends Component {
  render() {
    return (
      <View style={styles.button}>
        <Button
          title="Submit"
          onPress={() => Alert.alert('Submit Button Pressed')}
        />
      </View>
    );
  }
}

const styles = StyleSheet.create({
  button: {
    paddingBottom: 30,
  },
});
