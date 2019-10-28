import React, {Component} from 'react';
import {
  StyleSheet,
  Button,
  View,
  Text,
  Alert,
} from 'react-native';
import Constants from 'expo-constants';
import { connect } from 'react-redux';

export default class BackButton extends Component {
  constructor(props) {
    super(props)
  }

  render() {
    return (
      <View style={styles.button}>
        <Button
          title="Back"
          onPress={this.props.previous}
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
