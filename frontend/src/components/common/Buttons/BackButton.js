import React, {Component} from 'react';
import {
  StyleSheet,
  Button,
  View,
} from 'react-native';

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
